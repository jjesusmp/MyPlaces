package com.app.jjesusmp.googleplacesapi.data.local;

import android.support.annotation.NonNull;

import com.app.jjesusmp.googleplacesapi.data.PlacesDataSource;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;
import com.app.jjesusmp.googleplacesapi.util.AppExecutors;

import java.util.List;

public class PlacesLocalDataSource implements PlacesDataSource.LocalDataSource {

    private static volatile PlacesLocalDataSource instance;

    private PlacesDao placesDao;

    private AppExecutors mAppExecutors;

    private static final Object sLock = new Object();

    private PlacesLocalDataSource(@NonNull AppExecutors appExecutors,
                                  @NonNull PlacesDao placesDao) {
        mAppExecutors = appExecutors;
        this.placesDao = placesDao;
    }

    public static PlacesLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                    @NonNull PlacesDao placesDao) {
        synchronized (sLock) {
            if (instance == null) {
                synchronized (PlacesLocalDataSource.class) {
                    if (instance == null) {
                        instance = new PlacesLocalDataSource(appExecutors, placesDao);
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public void getSavedPlaces(GetSavedPlacesCallback callback) {
        mAppExecutors.diskIO().execute(() -> {
            final List<Place> places = placesDao.getAllPlaces();

            mAppExecutors.mainThread().execute(() -> {
                if (places != null) {
                    callback.onGetSavedPlacesOK(places);
                } else {
                    callback.onGetSavedPlacesKO();
                }
            });
        });
    }

    @Override
    public void savePlace(Place place) {
        mAppExecutors.diskIO().execute(() -> placesDao.insertPlace(place));
    }

    @Override
    public void deletePlace(Place place) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                placesDao.deleteTaskById(place.getId());
            }
        });
    }
}
