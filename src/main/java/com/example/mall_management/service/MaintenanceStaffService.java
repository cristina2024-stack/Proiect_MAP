package com.example.mall_management.service;

import com.example.mall_management.model.MaintenanceStaff;
import com.example.mall_management.model.StaffAssignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceStaffService {

    private List<MaintenanceStaff> staffList = new ArrayList<>();

    public List<MaintenanceStaff> getAllStaff() {
        return staffList;
    }

    public Optional<MaintenanceStaff> getStaffById(String id) {
        for (MaintenanceStaff s : staffList) {
            if (s.getId().equals(id)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public MaintenanceStaff addStaff(MaintenanceStaff staff) {
        staffList.add(staff);
        return staff;
    }

    public MaintenanceStaff updateStaff(String id, MaintenanceStaff updatedStaff) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getId().equals(id)) {
                staffList.set(i, updatedStaff);
                return updatedStaff;
            }
        }

        staffList.add(updatedStaff);
        return updatedStaff;
    }

    public boolean deleteStaff(String id) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getId().equals(id)) {
                staffList.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<MaintenanceStaff> getStaffByType(MaintenanceStaff.Type type) {
        List<MaintenanceStaff> result = new ArrayList<>();
        for (MaintenanceStaff s : staffList) {
            if (s.getType() == type) {
                result.add(s);
            }
        }
        return result;
    }

    public boolean addAssignmentToStaff(String staffId, StaffAssignment assignment) {
        for (MaintenanceStaff s : staffList) {
            if (s.getId().equals(staffId)) {
                s.addAssignment(assignment);
                return true;
            }
        }
        return false;
    }

    public List<StaffAssignment> getAssignmentsByStaff(String staffId) {
        for (MaintenanceStaff s : staffList) {
            if (s.getId().equals(staffId)) {
                return s.getAssignments();
            }
        }
        return new ArrayList<>();
    }
}
