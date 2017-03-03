package com.data.models;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class EventResponse {
    private String message;
    private PlanedEvent[] events;

    public EventResponse() {
    }

    public EventResponse(String message, PlanedEvent[] events) {
        this.message = message;
        this.events = events;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlanedEvent[] getEvents() {
        return events;
    }

    public void setEvents(PlanedEvent[] events) {
        this.events = events;
    }
}
