// src/main/java/com/example/mall_management/service/StaffAssignmentService.java
package com.example.mall_management.service;

import com.example.mall_management.model.StaffAssignment;
import com.example.mall_management.repository.StaffAssignment_Repository;
import java.util.List;


public class StaffAssignmentService {

    private final StaffAssignment_Repository repository;


    public StaffAssignmentService() {
        this.repository = new StaffAssignment_Repository();
    }

    public StaffAssignmentService(StaffAssignment_Repository repository) {
        this.repository = repository;
    }


    public void addAssignment(StaffAssignment assignment) {
        repository.save(assignment);
    }


    public void updateAssignment(StaffAssignment assignment) {
        repository.update(assignment);
    }

    public void deleteAssignment(String id) {
        repository.delete(id);
    }

    public StaffAssignment getAssignmentById(String id) {
        return repository.findById(id);
    }

    public List<StaffAssignment> getAllAssignments() {
        return repository.findAll();
    }

    public List<StaffAssignment> getAssignmentsByFloor(String floorId) {
        return repository.findByFloorId(floorId);
    }

    public List<StaffAssignment> getAssignmentsByStaff(String staffId) {
        return repository.findByStaffId(staffId);
    }

    public List<StaffAssignment> getAssignmentsByShift(StaffAssignment.Shift shift) {
        return repository.findByShift(shift);
    }

    public StaffAssignment getAssignmentByStaffAndShift(String staffId, StaffAssignment.Shift shift) {
        return repository.findByStaffAndShift(staffId, shift);
    }
}
