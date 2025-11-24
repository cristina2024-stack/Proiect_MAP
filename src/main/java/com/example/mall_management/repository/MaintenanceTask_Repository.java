package com.example.mall_management.repository;

import com.example.mall_management.model.MaintenanceTask;
import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceTask_Repository extends InFileRepository<MaintenanceTask> {

    public MaintenanceTask_Repository() {
        super(

                "src/main/resources/data/MaintenanceTask.json",
                MaintenanceTask.class
        );
    }

    public MaintenanceTask findByAssignmentId(String assignmentId) {
        return findAll().stream()
                .filter(t -> assignmentId != null && assignmentId.equals(t.getAssignmentId()))
                .findFirst()
                .orElse(null);
    }
}
