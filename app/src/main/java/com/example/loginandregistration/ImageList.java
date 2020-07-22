package com.example.loginandregistration;

public class ImageList {

    //initialization
    private String imageUrl;
    //parameterized constructor
    public ImageList(String imageUrl){
        this.imageUrl = imageUrl;
    }
    //default constructor
    public ImageList() {
    }
    //getters
    public String getImageUrl() {
        return imageUrl;
    }
    //setters
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
