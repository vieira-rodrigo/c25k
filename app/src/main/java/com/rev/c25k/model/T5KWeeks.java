package com.rev.c25k.model;

public enum T5KWeeks {
    W1("Week 1", 30, 8.2f, 120, 5.5f, 8),
    W2("Week 2", 30, 8.2f, 60, 5.5f, 15),
    W3("Week 3", 60, 8.2f, 60, 5.5f, 12),
    W4("Week 4", 120, 8.2f, 60, 5.5f, 8),
    W5("Week 5", 120, 8.2f, 30, 5.5f, 10),
    W6("Week 6", 240, 8.1f, 60, 5.5f, 6),
    W7("Week 7", 240, 8.8f, 45, 5.5f, 6),
    W8("Week 8", 300, 9f, 60, 5.5f, 5),
    W9("Week 9", 300, 9f, 45, 5.5f, 5),
    W10("Week 10", 360, 8.7f, 60, 5.5f, 5),
    W11("Week 11", 420, 9f, 60, 5.5f, 4),
    W12("Week 12", 480, 9f, 30, 5.5f, 4);

    private final String label;
    private final int secondsToRun;
    private final int secondsToWalk;
    private final int sets;
    private final float runSpeed;
    private final float walkSpeed;

    T5KWeeks(String label, int secondsToRun, float runSpeed, int secondsToWalk,
             float walkSpeed, int sets) {
        this.label = label;
        this.secondsToRun = secondsToRun;
        this.secondsToWalk = secondsToWalk;
        this.sets = sets;
        this.runSpeed = runSpeed;
        this.walkSpeed = walkSpeed;
    }

    public String getLabel() {
        return label;
    }

    public int getSecondsToRun() {
        return secondsToRun;
    }

    public int getSecondsToWalk() {
        return secondsToWalk;
    }

    public int getSets() {
        return sets;
    }

    public float getRunSpeed() {
        return runSpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }
}
