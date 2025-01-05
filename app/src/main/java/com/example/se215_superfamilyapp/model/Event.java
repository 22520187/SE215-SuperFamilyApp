package com.example.se215_superfamilyapp.model;

public class Event {
    private String eventName;
    private String startTime;
    private String endTime;

    public Event(String eventName, String startTime, String endTime) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
