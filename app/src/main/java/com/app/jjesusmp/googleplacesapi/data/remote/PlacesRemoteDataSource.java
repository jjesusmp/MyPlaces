package com.app.jjesusmp.googleplacesapi.data.remote;

import com.app.jjesusmp.googleplacesapi.data.PlacesDataSource;
import com.app.jjesusmp.googleplacesapi.data.remote.api.GooglePlacesService;
import com.app.jjesusmp.googleplacesapi.data.remote.api.RetrofitClient;
import com.app.jjesusmp.googleplacesapi.data.remote.model.GooglePlacesResponse;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesRemoteDataSource implements PlacesDataSource.RemoteDataSource {

    private static PlacesRemoteDataSource instance;
    private final String API_KEY = "YOUR_API_KEY";

    private PlacesRemoteDataSource() {
    }

    public static PlacesRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new PlacesRemoteDataSource();
        }
        return instance;
    }

    @Override
    public void getPlaceList(String str, final GetPlaceListCallback callback) {
        GooglePlacesService service = RetrofitClient.getInstance().create(GooglePlacesService.class);

        Call<GooglePlacesResponse> placeList = service.getPlaces(str, API_KEY);
        placeList.enqueue(new Callback<GooglePlacesResponse>() { //enque do the request async
            @Override
            public void onResponse(Call<GooglePlacesResponse> call, Response<GooglePlacesResponse> response) {
                GooglePlacesResponse candidateList = response.body();
                if(candidateList.getResults().size()>0){
                    callback.onGetPlaceListOK(candidateList.getResults());
                } else {
                    callback.onGetPlaceListKO();
                }
            }

            @Override
            public void onFailure(Call<GooglePlacesResponse> call, Throwable t) {
                callback.onGetPlaceListKO();
            }
        });
    }
}
