package com.app.jjesusmp.googleplacesapi.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.app.jjesusmp.googleplacesapi.domain.model.Place;

import java.util.List;

@Dao
public interface PlacesDao {

    @Query("SELECT * FROM places")
    List<Place> getAllPlaces();

    @Query("SELECT * FROM places WHERE id = :placeId")
    Place getPlaceById(String placeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlace(Place place);

    @Update
    int updatePlace(Place place);

    @Query("DELETE FROM places WHERE id = :placeId")
    int deleteTaskById(String placeId);

    @Query("DELETE FROM places")
    void deletePlaces();

}
