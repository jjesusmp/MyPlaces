package com.app.jjesusmp.googleplacesapi.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "places")
public class Place {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    private String icon;

    private String formattedAddress;

    private String name;

    private Integer priceLevel;

    private Double rating;

    private String reference;

    private Integer userRatingsTotal;

    public Place(@NonNull String id, String icon, String formattedAddress, String name, Integer priceLevel, Double rating, String reference, Integer userRatingsTotal) {
        this.id = id;
        this.icon = icon;
        this.formattedAddress = formattedAddress;
        this.name = name;
        this.priceLevel = priceLevel;
        this.rating = rating;
        this.reference = reference;
        this.userRatingsTotal = userRatingsTotal;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

}