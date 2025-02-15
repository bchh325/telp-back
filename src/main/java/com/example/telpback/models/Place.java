package com.example.telpback.models;

public class Place {

    private String imageUrl;
    private Integer likeAmount;
    private String location;
    private Integer visitAmount;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(Integer likeAmount) {
        this.likeAmount = likeAmount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getVisitAmount() {
        return visitAmount;
    }

    public void setVisitAmount(Integer visitAmount) {
        this.visitAmount = visitAmount;
    }

    @Override
    public String toString() {
        return "Place{" +
                "imageUrl='" + imageUrl + '\'' +
                ", likeAmount=" + likeAmount +
                ", location='" + location + '\'' +
                ", visitAmount=" + visitAmount +
                '}';
    }
}