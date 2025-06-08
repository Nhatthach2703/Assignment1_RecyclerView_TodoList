package com.nhatthach.assignment1_recyclerview_todolist.model;

public class Task {
    private String title;
    private boolean isCompleted;
    private long createdTime;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
        this.createdTime = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void toggleCompleted() {
        isCompleted = !isCompleted;
    }
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
