package com.nhatthach.assignment1_recyclerview_todolist.model;

public class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleCompleted() {
        isCompleted = !isCompleted;
    }
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }
}
