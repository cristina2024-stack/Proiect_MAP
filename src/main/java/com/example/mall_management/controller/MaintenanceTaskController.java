package com.example.mall_management.controller;

import com.example.mall_management.model.MaintenanceTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/maintenance-tasks")
public class MaintenanceTaskController {

    // "bază de date" simplă în memorie
    private final List<MaintenanceTask> tasks = new ArrayList<>();

    // LISTARE TASK-URI
    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "maintenance-tasks/index";   // templates/maintenance-tasks/index.html
    }

    // FORMULAR CREARE
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("taskForm", new MaintenanceTask());
        model.addAttribute("statuses", MaintenanceTask.Status.values());
        return "maintenance-tasks/form";    // templates/maintenance-tasks/form.html
    }

    // CREARE TASK
    @PostMapping
    public String createTask(@ModelAttribute("taskForm") MaintenanceTask form) {
        // aici poți pune validări sau generare ID dacă e gol
        tasks.add(form);
        return "redirect:/maintenance-tasks";
    }

    // ȘTERGERE TASK
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable String id) {
        tasks.removeIf(t -> id.equals(t.getId()));
        return "redirect:/maintenance-tasks";
    }
}
