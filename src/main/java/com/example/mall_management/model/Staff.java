package com.example.mall_management.model;

public class Staff {
    private String id;
    private String name;

    // ✅ Constructor fără parametri – necesar pentru subclase (ex: MaintenanceStaff)
    //   și pentru ca Spring / Thymeleaf să poată face binding automat.
    public Staff() {
    }

    // ✅ Constructor complet – utilizat în cod pentru inițializare explicită
    public Staff(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
