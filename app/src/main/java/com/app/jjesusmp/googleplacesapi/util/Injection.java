package com.app.jjesusmp.googleplacesapi.util;

import android.content.Context;

import com.app.jjesusmp.googleplacesapi.data.PlacesRepository;
import com.app.jjesusmp.googleplacesapi.data.local.PlacesDatabase;
import com.app.jjesusmp.googleplacesapi.data.local.PlacesLocalDataSource;
import com.app.jjesusmp.googleplacesapi.data.remote.PlacesRemoteDataSource;
import com.app.jjesusmp.googleplacesapi.domain.UseCaseScheduler;
import com.app.jjesusmp.googleplacesapi.domain.usecase.GetSavedPlacesUseCase;
import com.app.jjesusmp.googleplacesapi.domain.usecase.RemovePlaceUseCase;
import com.app.jjesusmp.googleplacesapi.domain.usecase.SavePlaceUseCase;
import com.app.jjesusmp.googleplacesapi.domain.usecase.SearchPlacesUseCase;

public class Injection {

    public static PlacesRepository providePlaceListRepository(Context context){
       return PlacesRepository.getInstance(PlacesRemoteDataSource.getInstance(), PlacesLocalDataSource.getInstance(new AppExecutors(), PlacesDatabase.getInstance(context).placeDao()));
    }

    public static SearchPlacesUseCase provideGetPlaceList(Context context){
        return new SearchPlacesUseCase(providePlaceListRepository(context));
    }

    public static GetSavedPlacesUseCase provideGetSavedPlacesUseCase(Context context){
        return new GetSavedPlacesUseCase(providePlaceListRepository(context));
    }

    public static SavePlaceUseCase provideSavePlaceUseCase(Context context){
        return new SavePlaceUseCase(providePlaceListRepository(context));
    }

    public static RemovePlaceUseCase provideRemovePlaceUseCase(Context context){
        return new RemovePlaceUseCase(providePlaceListRepository(context));
    }

    public static UseCaseScheduler provideUseCaseScheduler(Context context) {
        return UseCaseScheduler.getInstance();
    }

}
