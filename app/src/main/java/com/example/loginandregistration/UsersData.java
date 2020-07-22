package com.example.loginandregistration;

public class UsersData {

    //initializations
    private String userId;
    private String username;
    private String email;
    private String gender;
    private String mobile;
    private String imageUrl;
    //Parameterized constructor
    public UsersData(String userId,String username,String email,String gender,String mobile,String imageUrl) {

        this.userId = userId;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
        this.imageUrl = imageUrl;
    }
    //Default constructor
    public UsersData() {
    }
    //Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
