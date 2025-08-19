package com.s23010658.doit;

public class Notification {
    private String message;
    private String timestamp;

    public Notification(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
