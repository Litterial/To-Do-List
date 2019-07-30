package com.example.demo.models;

public enum Importance {
    HIGH("High"),
    LOW("Low");

    private final String level;
    Importance(String level)
    {
        this.level=level;
    }
}
