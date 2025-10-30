// model/MaintenanceStaff.java
package com.example.mall_management.model;



import java.util.ArrayList;
import java.util.List;

public class MaintenanceStaff extends Staff {
    public enum Type { ELECTRICAL, CLEANING }

    private Type type;
    private List<StaffAssignment> assignments = new ArrayList<>();

    public MaintenanceStaff(String id, String name, Type type) {
        super(id, name);
        this.type = type;
    }

    // Getters and setters
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public List<StaffAssignment> getAssignments() { return assignments; }
    public void addAssignment(StaffAssignment assignment) {
        assignments.add(assignment);
    }
}