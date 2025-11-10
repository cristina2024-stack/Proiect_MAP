package com.example.mall_management.repository;

import com.example.mall_management.model.MaintenanceStaff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MaintenanceStaff_Repository extends InFileRepository<MaintenanceStaff> {

    public MaintenanceStaff_Repository() {
        super(
                "src/main/resources/data/maintenance_staff.json",
                new EntityAdapter<MaintenanceStaff>() {
                    @Override
                    public String getId(MaintenanceStaff s) {
                        return s.getId();
                    }
                    @Override
                    public void setId(MaintenanceStaff s, String id) {
                        s.setId(id);
                    }
                    @Override
                    public void validate(MaintenanceStaff s) {
                        // Validări opționale specifice entității (dacă dorești)
                        // if (s.getName() == null || s.getName().isBlank())
                        //     throw new IllegalArgumentException("Numele este obligatoriu");
                    }
                }
        );
    }

    // --------- Metode personalizate (păstrate și simplificate) ---------

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
