package com.example.mall_management.repository;

import com.example.mall_management.model.MaintenanceTask;
import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceTask_Repository extends InFileRepository<MaintenanceTask> {

    public MaintenanceTask_Repository() {
        super(
                "src/main/resources/data/maintenance_task.json",
                new EntityAdapter<MaintenanceTask>() {

                    @Override
                    public String getId(MaintenanceTask t) {
                        return t.getId();
                    }

                    @Override
                    public void setId(MaintenanceTask t, String id) {
                        // ✅ nu mai accesăm câmpul privat direct
                        t.setId(id);
                    }

                    @Override
                    public void validate(MaintenanceTask t) {
                        if (t.getDescription() == null || t.getDescription().isBlank()) {
                            throw new IllegalArgumentException("Description cannot be empty");
                        }
                        if (t.getStatus() == null) {
                            throw new IllegalArgumentException("Status cannot be null");
                        }
                    }
                }
        );
    }

    // ----------- metode custom (dacă ai nevoie) ------------
    public MaintenanceTask findByAssignmentId(String assignmentId) {
        return findAll().stream()
                .filter(t -> assignmentId != null && assignmentId.equals(t.getAssignmentId()))
                .findFirst()
                .orElse(null);
    }
}
