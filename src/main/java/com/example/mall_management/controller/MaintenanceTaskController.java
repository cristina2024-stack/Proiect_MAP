package com.example.mall_management.controller;

import com.example.mall_management.model.MaintenanceTask;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maintenance-tasks")
public class MaintenanceTaskController {

    // "Bază de date" în memorie
    private List<MaintenanceTask> tasks = new ArrayList<>();

    @GetMapping
    public List<MaintenanceTask> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Optional<MaintenanceTask> getTaskById(@PathVariable String id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    public MaintenanceTask addTask(@RequestBody MaintenanceTask task) {
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public MaintenanceTask updateTask(@PathVariable String id, @RequestBody MaintenanceTask updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.set(i, updatedTask);
                return updatedTask;
            }
        }

        tasks.add(updatedTask);
        return updatedTask;
    }

    @PatchMapping("/{id}/status")
    public String updateTaskStatus(@PathVariable String id, @RequestParam MaintenanceTask.Status status) {
        Optional<MaintenanceTask> taskOpt = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (taskOpt.isPresent()) {
            taskOpt.get().setStatus(status);
            return "Status updated successfully.";
        } else {
            return "Task not found.";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));
        return removed ? "Task deleted successfully." : "Task not found.";
    }
}
