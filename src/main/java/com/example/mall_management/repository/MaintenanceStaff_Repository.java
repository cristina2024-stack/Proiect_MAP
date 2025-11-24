package com.example.mall_management.repository;

import com.example.mall_management.model.MaintenanceStaff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MaintenanceStaff_Repository extends InFileRepository<MaintenanceStaff> {

    public MaintenanceStaff_Repository() {
        super(
                "src/main/resources/data/MaintenanceStaff.json", // <-- EXACT ca numele fișierului tău
                MaintenanceStaff.class
        );
    }

    public List<MaintenanceStaff> findByType(MaintenanceStaff.Type type) {
        List<MaintenanceStaff> result = new ArrayList<>();
        for (MaintenanceStaff staff : findAll()) {
            if (staff.getType() == type) {
                result.add(staff);
            }
        }
        return result;
    }

    public List<MaintenanceStaff> findWithAtLeastAssignments(int minAssignments) {
        List<MaintenanceStaff> result = new ArrayList<>();
        for (MaintenanceStaff staff : findAll()) {
            int count = (staff.getAssignments() == null) ? 0 : staff.getAssignments().size();
            if (count >= minAssignments) {
                result.add(staff);
            }
        }
        return result;
    }

    public MaintenanceStaff findByName(String name) {
        if (name == null) return null;
        for (MaintenanceStaff staff : findAll()) {
            if (staff.getName() != null && staff.getName().equalsIgnoreCase(name)) {
                return staff;
            }
        }
        return null;
    }
}
