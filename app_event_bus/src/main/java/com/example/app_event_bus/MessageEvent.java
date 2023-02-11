package com.example.app_event_bus;

public class MessageEvent {
    private String message;
    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
