package com.example.telpback.models;

public class Place {

    private String image_url;
    private Integer like_amount;
    private String location;
    private Integer visit_amount;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getLike_amount() {
        return like_amount;
    }

    public void setLike_amount(int like_amount) {
        this.like_amount = like_amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getVisit_amount() {
        return visit_amount;
    }

    public void setVisit_amount(int visit_amount) {
        this.visit_amount = visit_amount;
    }

    @Override
    public String toString() {
        return "Place{" +
                "image_url='" + image_url + '\'' +
                ", like_amount=" + like_amount +
                ", location='" + location + '\'' +
                ", visit_amount=" + visit_amount +
                '}';
    }
}
