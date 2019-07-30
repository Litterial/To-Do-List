package com.example.demo.models;

public enum Urgency {
    HIGH("High"),
    LOW("Low");

    private final String level;
    Urgency(String level)
    {
        this.level=level;
    }

    public String getLevel() {
        return level;
    }
}
