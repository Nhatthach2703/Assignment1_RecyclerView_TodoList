package com.nhatthach.assignment1_recyclerview_todolist.model;

public class Task {
    private String title;
    private boolean isCompleted;
    private long createdTime;
    private String deadline; // Định dạng yyyy-MM-dd

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
        this.createdTime = System.currentTimeMillis();
        this.deadline = null;
    }
    public Task(String title, String deadline) {
        this.title = title;
        this.isCompleted = false;
        this.createdTime = System.currentTimeMillis();
        this.deadline = deadline;
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

    public String getDeadline() {
        return deadline;
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
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
