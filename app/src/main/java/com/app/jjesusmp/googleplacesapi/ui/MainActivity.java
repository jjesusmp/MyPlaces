package com.app.jjesusmp.googleplacesapi.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.jjesusmp.googleplacesapi.R;
import com.app.jjesusmp.googleplacesapi.util.ActivityUtil;
import com.app.jjesusmp.googleplacesapi.util.Injection;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MyPlacesFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MyPlacesFragment fragment = MyPlacesFragment.newInstance();

        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.cv_fragment);

        new MyPlacesPresenter(fragment, Injection.provideGetPlaceList(this), Injection.provideSavePlaceUseCase(this), Injection.provideGetSavedPlacesUseCase(this), Injection.provideRemovePlaceUseCase(this), Injection.provideUseCaseScheduler(this));
    }
}
