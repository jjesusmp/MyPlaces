package com.app.jjesusmp.googleplacesapi.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.app.jjesusmp.googleplacesapi.domain.model.Place;

@Database(entities = {Place.class}, version = 1, exportSchema = false)
public abstract class PlacesDatabase extends RoomDatabase {

    private static PlacesDatabase instance;

    public abstract PlacesDao placeDao();

    private static final Object sLock = new Object();

    public static PlacesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        PlacesDatabase.class, "MyPlaces.db")
                        .build();
            }
            return instance;
        }
    }
}
