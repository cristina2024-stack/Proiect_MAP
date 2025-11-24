package com.example.mall_management.model;

import java.util.Objects;

public class MaintenanceTask {

    public enum Status { PLANNED, ACTIVE, DONE }

    private String id;
    private String description;
    private Status status;
    private String assignmentId;

    public MaintenanceTask() {
    }

    public MaintenanceTask(String id, String description, Status status, String assignmentId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.assignmentId = assignmentId;
    }

    // GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    // EQUALS & HASHCODE (IMPORTANT!)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceTask)) return false;
        MaintenanceTask that = (MaintenanceTask) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
