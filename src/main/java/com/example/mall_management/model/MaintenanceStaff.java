package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceStaff extends Staff {

    public enum Type { ELECTRICAL, CLEANING }

    private Type type;
    private List<StaffAssignment> assignments = new ArrayList<>();

    // Constructor principal — folosit în controller
    public MaintenanceStaff(String id, String name, Type type) {
        super(id, name);
        this.type = type;
    }

    // Constructor fără parametri — necesar pentru Spring și serializare
    public MaintenanceStaff() {
        super();
    }

    // ✅ Getters și Setters proprii
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<StaffAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<StaffAssignment> assignments) {
        this.assignments = assignments;
    }

    public void addAssignment(StaffAssignment assignment) {
        if (assignment != null) {
            assignments.add(assignment);
        }
    }
}
