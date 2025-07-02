package com.leonardosanner.programming_courses.entity.course;

public enum CourseActive {

    ONLINE(true),
    OFFLINE(false);

    private final boolean active;

    public boolean getActiveStatus() {
        return this.active;
    }

    CourseActive(boolean bool) {
        this.active = bool;
    }
}
