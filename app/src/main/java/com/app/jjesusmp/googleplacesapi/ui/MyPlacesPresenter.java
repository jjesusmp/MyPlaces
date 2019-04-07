package com.app.jjesusmp.googleplacesapi.ui;

import com.app.jjesusmp.googleplacesapi.domain.UseCase;
import com.app.jjesusmp.googleplacesapi.domain.UseCaseScheduler;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;
import com.app.jjesusmp.googleplacesapi.domain.usecase.GetSavedPlacesUseCase;
import com.app.jjesusmp.googleplacesapi.domain.usecase.RemovePlaceUseCase;
import com.app.jjesusmp.googleplacesapi.domain.usecase.SavePlaceUseCase;
import com.app.jjesusmp.googleplacesapi.domain.usecase.SearchPlacesUseCase;

public class MyPlacesPresenter {

    private final IPlaceListView mPlaceListView;
    private final SearchPlacesUseCase searchPlacesUseCase;
    private final SavePlaceUseCase savePlaceUseCase;
    private final GetSavedPlacesUseCase getSavedPlacesUseCase;
    private final RemovePlaceUseCase removePlaceUseCase;
    private final UseCaseScheduler mUseCaseScheduler;

    public MyPlacesPresenter(IPlaceListView mPlaceListView, SearchPlacesUseCase searchPlacesUseCase, SavePlaceUseCase savePlaceUseCase, GetSavedPlacesUseCase getSavedPlacesUseCase, RemovePlaceUseCase removePlaceUseCase, UseCaseScheduler mUseCaseScheduler) {
        this.mPlaceListView = mPlaceListView;
        this.searchPlacesUseCase = searchPlacesUseCase;
        this.savePlaceUseCase = savePlaceUseCase;
        this.getSavedPlacesUseCase = getSavedPlacesUseCase;
        this.removePlaceUseCase = removePlaceUseCase;
        this.mUseCaseScheduler = mUseCaseScheduler;

        mPlaceListView.setPresenter(this);
    }

    public void SearchPlaces(String str) {
        mPlaceListView.showLoading();
        mUseCaseScheduler.execute(searchPlacesUseCase, new SearchPlacesUseCase.RequestValues(str), new UseCase.UseCaseCallback<SearchPlacesUseCase.ResponseValue>() {
            @Override
            public void onSuccess(SearchPlacesUseCase.ResponseValue responseValue) {
                mPlaceListView.loadData(responseValue.getPlaceList());
                mPlaceListView.hideLoading();
            }

            @Override
            public void onError() {
                mPlaceListView.hideLoading();
                mPlaceListView.showError();
            }
        });
    }

    public void savePlace(Place place) {
        mPlaceListView.showLoading();
        mUseCaseScheduler.execute(savePlaceUseCase, new SavePlaceUseCase.RequestValues(place), new UseCase.UseCaseCallback<SavePlaceUseCase.ResponseValue>() {
            @Override
            public void onSuccess(SavePlaceUseCase.ResponseValue responseValue) {
                mPlaceListView.hideLoading();
                mPlaceListView.showMessage("Place saved.");
            }

            @Override
            public void onError() {
                mPlaceListView.hideLoading();
                mPlaceListView.showError();
            }
        });
    }

    public void removePlace(Place place) {
        mPlaceListView.showLoading();
        mUseCaseScheduler.execute(removePlaceUseCase, new RemovePlaceUseCase.RequestValues(place), new UseCase.UseCaseCallback<RemovePlaceUseCase.ResponseValue>() {
            @Override
            public void onSuccess(RemovePlaceUseCase.ResponseValue responseValue) {
                mPlaceListView.hideLoading();
                mPlaceListView.showMessage("Place removed");
            }

            @Override
            public void onError() {
                mPlaceListView.hideLoading();
                mPlaceListView.showError();
            }
        });
    }


    public void getSavedPlaves() {
        mPlaceListView.showLoading();
        mUseCaseScheduler.execute(getSavedPlacesUseCase, new GetSavedPlacesUseCase.RequestValues(), new UseCase.UseCaseCallback<GetSavedPlacesUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetSavedPlacesUseCase.ResponseValue responseValue) {
                mPlaceListView.loadData(responseValue.getPlaceList());
                mPlaceListView.hideLoading();
            }

            @Override
            public void onError() {
                mPlaceListView.hideLoading();
                mPlaceListView.showError();
            }
        });
    }


}
