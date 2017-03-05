package com.data.models;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class PlanedEvent {
    private String title;
    private String description;
    private String start;
    private String end;
    private String id;
    private SimpleDate date;
    private Boolean isUserIn;

    public PlanedEvent(){

    }

    public PlanedEvent(String end, String start, String description, String title, SimpleDate date) {
        this.setEnd(end);
        this.setStart(start);
        this.setDescription(description);
        this.setTitle(title);
        this.setDate(date);
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SimpleDate getDate() {
        return date;
    }

    public void setDate(SimpleDate date) {
        this.date = date;
    }

    public Boolean getUserIn() {
        return isUserIn;
    }

    public void setUserIn(Boolean userIn) {
        isUserIn = userIn;
    }
}
