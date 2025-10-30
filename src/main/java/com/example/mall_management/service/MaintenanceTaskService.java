package com.example.mall_management.service;

import com.example.mall_management.model.MaintenanceTask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceTaskService {

    private List<MaintenanceTask> tasks = new ArrayList<>();

    public List<MaintenanceTask> getAllTasks() {
        return tasks;
    }

    public Optional<MaintenanceTask> getTaskById(String id) {
        for (MaintenanceTask t : tasks) {
            if (t.getId().equals(id)) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    public MaintenanceTask addTask(MaintenanceTask task) {
        tasks.add(task);
        return task;
    }

    public MaintenanceTask updateTask(String id, MaintenanceTask updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.set(i, updatedTask);
                return updatedTask;
            }
        }
        // dacă nu există, îl adăugăm
        tasks.add(updatedTask);
        return updatedTask;
    }

    public boolean deleteTask(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateTaskStatus(String id, MaintenanceTask.Status newStatus) {
        for (MaintenanceTask t : tasks) {
            if (t.getId().equals(id)) {
                t.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    public List<MaintenanceTask> getTasksByStatus(MaintenanceTask.Status status) {
        List<MaintenanceTask> result = new ArrayList<>();
        for (MaintenanceTask t : tasks) {
            if (t.getStatus() == status) {
                result.add(t);
            }
        }
        return result;
    }

    public List<MaintenanceTask> getTasksByAssignment(String assignmentId) {
        List<MaintenanceTask> result = new ArrayList<>();
        for (MaintenanceTask t : tasks) {
            if (t.getAssignmentId().equals(assignmentId)) {
                result.add(t);
            }
        }
        return result;
    }
}
