package com.davidchristie.taskapi.task;

import java.time.Instant;
import java.util.Date;

public class Task {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private final Date createDate;
    private Date completedDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.createDate = Date.from(Instant.now());
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateCompleted(boolean completed) {
        this.completed = completed;
        if (this.completed) {
            this.completedDate = Date.from(Instant.now());
        } else {
            this.completedDate = null;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", createDate=" + createDate +
                ", completedDate=" + completedDate +
                '}';
    }
}
