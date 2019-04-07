package com.app.jjesusmp.googleplacesapi.util;

import com.app.jjesusmp.googleplacesapi.data.remote.model.Result;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;

import java.util.ArrayList;
import java.util.List;

public class MappingUtil {

    public static List<Place> toDomainModel (List<Result> results){
        List<Place> places = new ArrayList<>();
        for (Result r: results){
            places.add(new Place(r.getId(),r.getIcon(),r.getFormattedAddress(),r.getName(),r.getPriceLevel(),r.getRating(),r.getReference(),r.getUserRatingsTotal()));
        }
        return places;
    }
}
