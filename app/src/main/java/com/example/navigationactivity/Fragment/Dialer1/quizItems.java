package com.example.navigationactivity.Fragment.Dialer1;

public class quizItems {
    private String name,rating;



    public quizItems(String name, String rating) {
        this.name = name;
        this.rating = rating;
    }

    public quizItems() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
