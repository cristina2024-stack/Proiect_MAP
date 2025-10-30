package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private String id;
    private int number;

    private List<Shop> shops = new ArrayList<>();
    private List<MaintenanceTask> tasks = new ArrayList<>();
    private List<ElectricalAsset> electricals = new ArrayList<>();
    private List<StaffAssignment> assignments = new ArrayList<>();

    public Floor(String id, int number) {
        this.id = id;
        this.number = number;
    }

    public void addShop(Shop s) { shops.add(s); }
    public void addTask(MaintenanceTask t) { tasks.add(t); }
    public void addElectrical(ElectricalAsset e) { electricals.add(e); }
    public void addAssignment(StaffAssignment a) { assignments.add(a); }

    public String getId() { return id; }
    public int getNumber() { return number; }
    public List<Shop> getShops() { return shops; }
    public List<MaintenanceTask> getTasks() { return tasks; }
    public List<ElectricalAsset> getElectricals() { return electricals; }
    public List<StaffAssignment> getAssignments() { return assignments; }
}
