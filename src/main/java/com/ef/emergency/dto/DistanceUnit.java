package com.ef.emergency.dto;

public enum DistanceUnit {
    Kilometers("km"),
    Meters("m");

    private final String shortcut;

    DistanceUnit(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return this.shortcut;
    }
}
