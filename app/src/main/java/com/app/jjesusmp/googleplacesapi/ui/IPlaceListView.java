package com.app.jjesusmp.googleplacesapi.ui;

import com.app.jjesusmp.googleplacesapi.domain.model.Place;

import java.util.List;

public interface IPlaceListView {

    void showLoading();

    void hideLoading();

    void loadData(List<Place> places);

    void showError();

    void showMessage(String str);

    void setPresenter(MyPlacesPresenter presenter);

}
