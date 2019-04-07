package com.app.jjesusmp.googleplacesapi.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.jjesusmp.googleplacesapi.R;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;
import com.app.jjesusmp.googleplacesapi.ui.adapter.PlaceAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPlacesFragment extends Fragment implements IPlaceListView, PlaceAdapter.OnItemClickListener {


    @BindView(R.id.et_filter)
    EditText et_filter;
    @BindView(R.id.rv_placeList)
    RecyclerView recyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    @BindView(R.id.ly_filter)
    ConstraintLayout ly_filter;


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    MyPlacesPresenter presenter;

    public MyPlacesFragment() {

    }

    public static MyPlacesFragment newInstance() {
        return new MyPlacesFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_list, container, false);
        ButterKnife.bind(this, view);
        configureUI();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadData(List<Place> places) {
        mAdapter = new PlaceAdapter(places, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();

    }

    @Override
    public void setPresenter(MyPlacesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void savePlace(Place place) {
        presenter.savePlace(place);
    }

    @Override
    public void removePlace(Place place) {
        presenter.removePlace(place);
    }

    @OnClick(R.id.btn_search)
    public void clickBtnSearch() {
        presenter.SearchPlaces(et_filter.getText().toString());
    }

    @OnClick(R.id.ly_favorites)
    public void clickBtnFavorite() {
        presenter.getSavedPlaves();
    }

    private void configureUI() {
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
