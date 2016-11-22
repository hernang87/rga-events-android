package com.rga.events.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by pablo on 13/11/16.
 */

public class Event extends FBObject {

    @Expose()
    private String title;

    @Expose()
    private String description;

    @Expose()
    private long date;

    @Expose()
    private int duration;

    @Expose()
    private String place;

    @Expose()
    private String cost;

    @Expose()
    private boolean recurrent;

    @Expose()
    private int maxUsers;

    @Expose()
    private String comments;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return new Date(date);
    }

    public int getDuration() {
        return duration;
    }

    public String getPlace() {
        return place;
    }

    public String getCost() {
        return cost;
    }

    public boolean isRecurrent() {
        return recurrent;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public String getComments() {
        return comments;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date.getTime();
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setRecurrent(boolean recurrent) {
        this.recurrent = recurrent;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
