package com.example.navigationactivity.Fragment;

public class UserProfile {
    public String userPhone;
    public String userEmail;
    public String uName;
    public String userName;
    public String userAddr;
    public String userGen;
    public String userAge;
//    public String rate_review;
//    public String ratings;
    public UserProfile(){
    }

    public UserProfile(String userPhone, String userEmail, String userName,String userAddr,String userGen,String userAge) {
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userAddr=userAddr;
        this.userGen=userGen;
        this.userAge=userAge;
//        this.rate_review=rate_review;
//        this.ratings = ratings;
    }


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserGen() {
        return userGen;
    }

    public void setUserGen(String userGen) {
        this.userGen = userGen;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

//    public String getRate_review() {
//        return rate_review;
//    }
//
//    public void setRate_review(String rate_review) {
//        this.rate_review = rate_review;
//    }
//
//    public String getRatings() {
//        return ratings;
//    }
//
//    public void setRatings(String ratings) {
//        this.ratings = ratings;
//    }
}