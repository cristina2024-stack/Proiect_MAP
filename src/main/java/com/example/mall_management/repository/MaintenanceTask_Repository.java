// src/main/java/com/example/mall_management/repository/MaintenanceTaskRepository.java
package com.example.mall_management.repository;

import com.example.mall_management.model.MaintenanceTask;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceTask_Repository extends InMemoryRepository<MaintenanceTask> {

    public MaintenanceTask_Repository() {
        super(MaintenanceTask.class);
    }

    public List<MaintenanceTask> findByStatus(MaintenanceTask.Status status) {
        List<MaintenanceTask> result = new ArrayList<MaintenanceTask>();
        List<MaintenanceTask> allTasks = findAll();

        for (int i = 0; i < allTasks.size(); i++) {
            MaintenanceTask task = allTasks.get(i);
            if (task.getStatus() == status) {
                result.add(task);
            }
        }

        return result;
    }

    public List<MaintenanceTask> findByAssignmentId(String assignmentId) {
        List<MaintenanceTask> result = new ArrayList<MaintenanceTask>();
        List<MaintenanceTask> allTasks = findAll();

        for (int i = 0; i < allTasks.size(); i++) {
            MaintenanceTask task = allTasks.get(i);
            if (task.getAssignmentId() != null && task.getAssignmentId().equals(assignmentId)) {
                result.add(task);
            }
        }

        return result;
    }

    public MaintenanceTask findByDescription(String description) {
        List<MaintenanceTask> allTasks = findAll();

        for (int i = 0; i < allTasks.size(); i++) {
            MaintenanceTask task = allTasks.get(i);
            if (task.getDescription().equalsIgnoreCase(description)) {
                return task;
            }
        }

        return null;
    }
}

