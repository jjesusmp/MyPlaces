package com.app.jjesusmp.googleplacesapi.data;

import com.app.jjesusmp.googleplacesapi.data.local.PlacesDatabase;
import com.app.jjesusmp.googleplacesapi.data.remote.model.Result;
import com.app.jjesusmp.googleplacesapi.util.MappingUtil;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;

import java.util.List;

public class PlacesRepository {


    public interface IRepositoryCallback {
        void getPlacesOK(List<Place> responseValue);

        void getPlacesKO();
    }

    private static PlacesRepository instance = null;

    private final PlacesDataSource.RemoteDataSource mRemotePlacesDataSource;
    private final PlacesDataSource.LocalDataSource mLocalPlacesDataSource;

    public static PlacesRepository getInstance(PlacesDataSource.RemoteDataSource mRemotePlacesDataSource, PlacesDataSource.LocalDataSource mLocalPlacesDataSource) {
        if (instance == null) {
            instance = new PlacesRepository(mRemotePlacesDataSource, mLocalPlacesDataSource);
        }
        return instance;
    }

    private PlacesRepository(PlacesDataSource.RemoteDataSource mRemotePlacesDataSource, PlacesDataSource.LocalDataSource mLocalPlacesDataSource) {
        this.mRemotePlacesDataSource = mRemotePlacesDataSource;
        this.mLocalPlacesDataSource = mLocalPlacesDataSource;
    }


    public void getPlaceList(String str, final IRepositoryCallback repositoryCallback) {
        mRemotePlacesDataSource.getPlaceList(str, new PlacesDataSource.RemoteDataSource.GetPlaceListCallback() {
            @Override
            public void onGetPlaceListOK(List<Result> results) {
                repositoryCallback.getPlacesOK(MappingUtil.toDomainModel(results));
            }

            @Override
            public void onGetPlaceListKO() {
                repositoryCallback.getPlacesKO();
            }
        });
    }

    public void getSavedPlaces(final IRepositoryCallback repositoryCallback) {
        mLocalPlacesDataSource.getSavedPlaces(new PlacesDataSource.LocalDataSource.GetSavedPlacesCallback() {
            @Override
            public void onGetSavedPlacesOK(List<Place> places) {
                repositoryCallback.getPlacesOK(places);
            }

            @Override
            public void onGetSavedPlacesKO() {
                repositoryCallback.getPlacesKO();
            }
        });
    }

    public void savePlace(Place place) {
        mLocalPlacesDataSource.savePlace(place);
    }

    public void deletePlace(Place place){
        mLocalPlacesDataSource.deletePlace(place);
    }

}
