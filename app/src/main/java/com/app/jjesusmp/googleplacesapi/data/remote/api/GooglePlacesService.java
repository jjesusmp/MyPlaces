package com.app.jjesusmp.googleplacesapi.data.remote.api;

import com.app.jjesusmp.googleplacesapi.data.remote.model.GooglePlacesResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GooglePlacesService {

    @POST("maps/api/place/textsearch/json?")
    Call<GooglePlacesResponse> getPlaces(@Query("query") String input, @Query("key") String key);

}