package com.data.models;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class EventResponse {
    private String message;
    private PlannedEvent[] events;

    public EventResponse() {
    }

    public EventResponse(String message, PlannedEvent[] events) {
        this.message = message;
        this.events = events;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlannedEvent[] getEvents() {
        return events;
    }

    public void setEvents(PlannedEvent[] events) {
        this.events = events;
    }
}
