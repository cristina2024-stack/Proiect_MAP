package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Floor {

    private String id;
    private int number;

    // lucrăm cu ID-uri, nu cu obiecte
    private List<String> shops = new ArrayList<>();
    private List<String> tasks = new ArrayList<>();
    private List<String> electricals = new ArrayList<>();
    private List<String> assignments = new ArrayList<>();

    // Constructor gol — necesar pentru Jackson și Spring
    public Floor() {
    }

    public Floor(String id, int number) {
        this.id = id;
        this.number = number;
    }

    // --------------------
    // GETTERS & SETTERS
    // --------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getShops() {
        return shops;
    }

    public void setShops(List<String> shops) {
        this.shops = shops;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public List<String> getElectricals() {
        return electricals;
    }

    public void setElectricals(List<String> electricals) {
        this.electricals = electricals;
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }

    // --------------------
    // METODE UTILE
    // --------------------

    public void addShopId(String shopId) {
        if (shopId != null && !shopId.isBlank()) {
            shops.add(shopId);
        }
    }

    public void addTaskId(String taskId) {
        if (taskId != null && !taskId.isBlank()) {
            tasks.add(taskId);
        }
    }

    public void addElectricalId(String assetId) {
        if (assetId != null && !assetId.isBlank()) {
            electricals.add(assetId);
        }
    }

    public void addAssignmentId(String assignmentId) {
        if (assignmentId != null && !assignmentId.isBlank()) {
            assignments.add(assignmentId);
        }
    }

    public int getShopCount() {
        return shops == null ? 0 : shops.size();
    }

    // --------------------
    // equals() & hashCode()
    // --------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Floor)) return false;
        Floor floor = (Floor) o;
        return Objects.equals(id, floor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
