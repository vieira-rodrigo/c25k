package com.rev.c25k.model;

public enum Training {
    T5K("5K");

    String label;

    Training(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
