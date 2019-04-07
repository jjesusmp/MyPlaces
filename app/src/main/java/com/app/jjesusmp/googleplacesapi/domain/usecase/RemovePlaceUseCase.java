package com.app.jjesusmp.googleplacesapi.domain.usecase;

import com.app.jjesusmp.googleplacesapi.data.PlacesRepository;
import com.app.jjesusmp.googleplacesapi.domain.UseCase;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;

public class RemovePlaceUseCase extends UseCase<RemovePlaceUseCase.RequestValues, RemovePlaceUseCase.ResponseValue> {

    private final PlacesRepository mPlaceListRespository;

    public RemovePlaceUseCase(PlacesRepository mPlaceListRespository) {
        this.mPlaceListRespository = mPlaceListRespository;
    }

    @Override
    protected void executeUseCase(RemovePlaceUseCase.RequestValues requestValues) {
        mPlaceListRespository.deletePlace(requestValues.getPlace());
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static class RequestValues implements UseCase.RequestValues {

        private final Place mPlace;

        public RequestValues(Place place) {
            this.mPlace = place;
        }

        public Place getPlace() {
            return mPlace;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
    }

}

