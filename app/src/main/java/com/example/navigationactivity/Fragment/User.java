package com.example.navigationactivity.Fragment;

public class User
{
    private String userName;
    private String userPhone;
    private String ratings;

    public User() {
    }

    public User(String userName, String userPhone, String ratings) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.ratings = ratings;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
