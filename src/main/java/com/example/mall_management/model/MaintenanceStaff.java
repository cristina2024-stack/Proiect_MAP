package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MaintenanceStaff extends Staff {

    public enum Type { ELECTRICAL, CLEANING }

    private Type type;
    private List<String> assignments = new ArrayList<>();

    // constructor gol pentru JSON / Spring
    public MaintenanceStaff() {
        super();
    }

    // constructor complet
    public MaintenanceStaff(String id, String name, Type type) {
        super(id, name);
        this.type = type;
    }

    // GETTERS & SETTERS

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }

    public void addAssignmentId(String assignmentId) {
        if (assignmentId != null && !assignmentId.isBlank()) {
            assignments.add(assignmentId);
        }
    }

    // equals & hashCode IMPORTANT pentru repository
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceStaff)) return false;
        MaintenanceStaff that = (MaintenanceStaff) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
