package com.app.jjesusmp.googleplacesapi.data;

import com.app.jjesusmp.googleplacesapi.data.remote.model.Result;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;

import java.util.List;

public class PlacesDataSource {

    public interface RemoteDataSource {
        interface GetPlaceListCallback {

            void onGetPlaceListOK(List<Result> results);

            void onGetPlaceListKO();
        }

        void getPlaceList(String str, GetPlaceListCallback callback);
    }

    public interface LocalDataSource {
        interface GetSavedPlacesCallback {

            void onGetSavedPlacesOK(List<Place> places);

            void onGetSavedPlacesKO();
        }

        void getSavedPlaces(GetSavedPlacesCallback callback);

        void savePlace(Place place);

        void deletePlace(Place place);
    }


}
