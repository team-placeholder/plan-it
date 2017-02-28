package com.data.models;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class PlannedEvent {
    private String title;
    private String description;
    private String start;
    private String end;

    public PlannedEvent(){

    }

    public PlannedEvent(String end, String start, String description, String title) {
        this.end = end;
        this.start = start;
        this.description = description;
        this.title = title;
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

}
