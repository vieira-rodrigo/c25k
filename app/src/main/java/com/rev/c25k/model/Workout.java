package com.rev.c25k.model;

public class Workout {

    public static final String TABLE_NAME = "WORKOUTS";
    public static final String _ID = "INT_ID";
    public static final String COLUMN_NAME_TRAINING = "TX_TRAINING";
    public static final String COLUMN_NAME_DATE = "TX_DATE";
    public static final String COLUMN_NAME_WEEK = "TX_WEEK";
    public static final String COLUMN_NAME_SETS = "TX_SETS";
    public static final String COLUMN_NAME_STATUS = "TX_STATUS";
    public static final String COLUMN_NAME_TIME = "TX_TIME";
    public static final String COLUMN_NAME_DISTANCE = "TX_DISTANCE";

    private Long id;
    private Training training;
    private String date;
    private T5KWeeks week;
    private String sets;
    private Status status;
    private String time;
    private String distance;

    public Workout(Long id, Training training, String date, T5KWeeks week, String sets,
                   Status status, String time, String distance) {
        this.id = id;
        this.training = training;
        this.date = date;
        this.week = week;
        this.sets = sets;
        this.status = status;
        this.time = time;
        this.distance = distance;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public T5KWeeks getWeek() {
        return week;
    }

    public void setWeek(T5KWeeks week) {
        this.week = week;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
