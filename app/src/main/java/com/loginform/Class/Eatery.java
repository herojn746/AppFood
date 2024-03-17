package com.loginform.Class;

import java.io.Serializable;

public class Eatery implements Serializable {
    public int MaQA;
    public String name, address, description;
    Float starRating;
    byte[] image;
    Double longlatitude;
    Double latitude;

    public int getMaQA() {
        return MaQA;
    }

    public void setMaQA(int maQA) {
        MaQA = maQA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getStarRating() {
        return starRating;
    }

    public void setStarRating(Float starRating) {
        this.starRating = starRating;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Double getLonglatitude() {
        return longlatitude;
    }

    public void setLonglatitude(Double longlatitude) {
        this.longlatitude = longlatitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Eatery(int maQA, String name, String address, String description, Float starRating, byte[] image, Double longlatitude, Double latitude) {
        MaQA = maQA;
        this.name = name;
        this.address = address;
        this.description = description;
        this.starRating = starRating;
        this.image = image;
        this.longlatitude = longlatitude;
        this.latitude = latitude;
    }

    public Eatery() {
    }
}
