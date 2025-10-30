
package com.example.mall_management.model;

public class StaffAssignment {
    public enum Shift { MORNING, EVENING, NIGHT }

    private String id;
    private String floorId;
    private String staffId;
    private Shift shift;

    public StaffAssignment(String id, String floorId, String staffId, Shift shift) {
        this.id = id;
        this.floorId = floorId;
        this.staffId = staffId;
        this.shift = shift;
    }

    public String getId() { return id; }
    public String getFloorId() { return floorId; }
    public String getStaffId() { return staffId; }
    public Shift getShift() { return shift; }
    public void setFloorId(String floorId) { this.floorId = floorId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public void setShift(Shift shift) { this.shift = shift; }
}