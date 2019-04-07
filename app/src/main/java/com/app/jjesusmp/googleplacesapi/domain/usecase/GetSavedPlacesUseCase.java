package com.app.jjesusmp.googleplacesapi.domain.usecase;

import com.app.jjesusmp.googleplacesapi.domain.model.Place;
import com.app.jjesusmp.googleplacesapi.domain.UseCase;
import com.app.jjesusmp.googleplacesapi.data.PlacesRepository;

import java.util.List;

public class GetSavedPlacesUseCase extends UseCase<GetSavedPlacesUseCase.RequestValues, GetSavedPlacesUseCase.ResponseValue> {

    private final PlacesRepository mPlaceListRespository;

    public GetSavedPlacesUseCase(PlacesRepository mPlaceListRespository) {
        this.mPlaceListRespository = mPlaceListRespository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
       mPlaceListRespository.getSavedPlaces(new PlacesRepository.IRepositoryCallback() {
           @Override
           public void getPlacesOK(List<Place> responseValue) {
               getUseCaseCallback().onSuccess(new GetSavedPlacesUseCase.ResponseValue(responseValue));
           }

           @Override
           public void getPlacesKO() {
               getUseCaseCallback().onError();
           }
       });
    }

    public static class RequestValues implements UseCase.RequestValues {
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final List<Place> placeList;

        public ResponseValue(List<Place> placeList) {
            this.placeList = placeList;
        }

        public List<Place> getPlaceList() {
            return placeList;
        }
    }

}
