package com.example.demo.models;

import java.time.LocalDateTime;

public class WorldClockResponse {
    private LocalDateTime currentDateTime;

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
