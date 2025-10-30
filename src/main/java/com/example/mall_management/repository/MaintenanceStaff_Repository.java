// src/main/java/com/example/mall_management/repository/MaintenanceStaffRepository.java
package com.example.mall_management.repository;

import com.example.mall_management.model.MaintenanceStaff;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository în memorie pentru gestionarea personalului de mentenanță.
 * Extinde InMemoryRepository pentru a moșteni operațiile CRUD.
 */
public class MaintenanceStaff_Repository extends InMemoryRepository<MaintenanceStaff> {

    public MaintenanceStaff_Repository() {
        super(MaintenanceStaff.class);
    }


    public List<MaintenanceStaff> findByType(MaintenanceStaff.Type type) {
        List<MaintenanceStaff> result = new ArrayList<MaintenanceStaff>();
        List<MaintenanceStaff> allStaff = findAll();

        for (int i = 0; i < allStaff.size(); i++) {
            MaintenanceStaff staff = allStaff.get(i);
            if (staff.getType() == type) {
                result.add(staff);
            }
        }

        return result;
    }

    public List<MaintenanceStaff> findWithAtLeastAssignments(int minAssignments) {
        List<MaintenanceStaff> result = new ArrayList<MaintenanceStaff>();
        List<MaintenanceStaff> allStaff = findAll();

        for (int i = 0; i < allStaff.size(); i++) {
            MaintenanceStaff staff = allStaff.get(i);
            int count = 0;
            if (staff.getAssignments() != null) {
                count = staff.getAssignments().size();
            }
            if (count >= minAssignments) {
                result.add(staff);
            }
        }

        return result;
    }

    public MaintenanceStaff findByName(String name) {
        List<MaintenanceStaff> allStaff = findAll();

        for (int i = 0; i < allStaff.size(); i++) {
            MaintenanceStaff staff = allStaff.get(i);
            if (staff.getName().equalsIgnoreCase(name)) {
                return staff;
            }
        }

        return null;
    }
}
