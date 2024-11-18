package com.employee.core.dto;

import java.time.LocalDateTime;

public class TaskRemind {

    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private LocalDateTime expirationDate;

    public TaskRemind() {
    }

    public TaskRemind(Long taskId, String taskTitle, String taskDescription, LocalDateTime expirationDate) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.expirationDate = expirationDate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
