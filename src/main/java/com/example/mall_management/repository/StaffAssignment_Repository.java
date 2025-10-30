// src/main/java/com/example/mall_management/repository/StaffAssignmentRepository.java
package com.example.mall_management.repository;

import com.example.mall_management.model.StaffAssignment;
import java.util.ArrayList;
import java.util.List;

public class StaffAssignment_Repository extends InMemoryRepository<StaffAssignment> {

    public StaffAssignment_Repository() {
        super(StaffAssignment.class);
    }

    public List<StaffAssignment> findByFloorId(String floorId) {
        List<StaffAssignment> result = new ArrayList<StaffAssignment>();
        List<StaffAssignment> allAssignments = findAll();

        for (int i = 0; i < allAssignments.size(); i++) {
            StaffAssignment assignment = allAssignments.get(i);
            if (assignment.getFloorId() != null && assignment.getFloorId().equals(floorId)) {
                result.add(assignment);
            }
        }

        return result;
    }

    public List<StaffAssignment> findByStaffId(String staffId) {
        List<StaffAssignment> result = new ArrayList<StaffAssignment>();
        List<StaffAssignment> allAssignments = findAll();

        for (int i = 0; i < allAssignments.size(); i++) {
            StaffAssignment assignment = allAssignments.get(i);
            if (assignment.getStaffId() != null && assignment.getStaffId().equals(staffId)) {
                result.add(assignment);
            }
        }

        return result;
    }

    public List<StaffAssignment> findByShift(StaffAssignment.Shift shift) {
        List<StaffAssignment> result = new ArrayList<StaffAssignment>();
        List<StaffAssignment> allAssignments = findAll();

        for (int i = 0; i < allAssignments.size(); i++) {
            StaffAssignment assignment = allAssignments.get(i);
            if (assignment.getShift() == shift) {
                result.add(assignment);
            }
        }

        return result;
    }

    public StaffAssignment findByStaffAndShift(String staffId, StaffAssignment.Shift shift) {
        List<StaffAssignment> allAssignments = findAll();

        for (int i = 0; i < allAssignments.size(); i++) {
            StaffAssignment assignment = allAssignments.get(i);
            if (assignment.getStaffId() != null && assignment.getStaffId().equals(staffId)
                    && assignment.getShift() == shift) {
                return assignment;
            }
        }

        return null;
    }
}
