package com.example.demo.models;

public enum Importance {
    HIGH("High"),
    LOW("Low");


    private String level;

    Importance(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }


}