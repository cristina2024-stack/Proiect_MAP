package com.example.mall_management.model;

public class MaintenanceTask {
    public enum Status { PLANNED, ACTIVE, DONE }

    private String id;
    private String description;
    private Status status;
    private String assignmentId;

    public MaintenanceTask(String id, String description, Status status, String assignmentId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.assignmentId = assignmentId;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }
    public String getAssignmentId() { return assignmentId; }
    public void setStatus(Status status) { this.status = status; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }
}