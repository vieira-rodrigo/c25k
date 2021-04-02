package com.rev.c25k.model;

public enum T5KWeeks {
    W1("Week 1", 30, 120, 8),
    W2("Week 2", 30, 60, 15),
    W3("Week 3", 60, 60, 12),
    W4("Week 4", 120, 60, 8),
    W5("Week 5", 120, 30, 10),
    W6("Week 6", 240, 60, 6),
    W7("Week 7", 240, 45, 6),
    W8("Week 8", 300, 60, 5),
    W9("Week 9", 300, 45, 5),
    W10("Week 10", 360, 60, 5),
    W11("Week 11", 420, 60, 4),
    W12("Week 12", 480, 30, 4);

    private String label;
    private int secondsToRun;
    private int secondsToWalk;
    private int sets;

    T5KWeeks(String label, int secondsToRun, int secondsToWalk, int sets) {
        this.label = label;
        this.secondsToRun = secondsToRun;
        this.secondsToWalk = secondsToWalk;
        this.sets = sets;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSecondsToRun() {
        return secondsToRun;
    }

    public void setSecondsToRun(int secondsToRun) {
        this.secondsToRun = secondsToRun;
    }

    public int getSecondsToWalk() {
        return secondsToWalk;
    }

    public void setSecondsToWalk(int secondsToWalk) {
        this.secondsToWalk = secondsToWalk;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
