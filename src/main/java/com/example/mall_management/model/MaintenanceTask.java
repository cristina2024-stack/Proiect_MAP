package com.example.mall_management.model;

public class MaintenanceTask {

    public enum Status { PLANNED, ACTIVE, DONE }

    private String id;
    private String description;
    private Status status;
    private String assignmentId;

    // ✅ Constructor gol necesar pentru Jackson / JSON deserialize
    public MaintenanceTask() {}

    // ✅ Constructor complet
    public MaintenanceTask(String id, String description, Status status, String assignmentId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.assignmentId = assignmentId;
    }

    // ✅ ID (getter + setter)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // ✅ Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ✅ Status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // ✅ Assignment ID
    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }
}
