package com.example.myapplication;

public class Student {
    private String name;
    private int score;
    private int hours;
    private String country;


    public Student(String name, int hours, String country) {
        this.name = name;
        this.hours = hours;
        this.country = country;
    }

    public void Student(String name, int score, String country) {
        this.name = name;
        this.score = score;
        this.country = country;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
