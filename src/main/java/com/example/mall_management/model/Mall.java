package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mall {

    private String id;
    private String name;
    private String city;

    // Lista de ID-uri de etaje
    private List<String> floors = new ArrayList<>();

    // Constructor gol (necesar pentru Jackson)
    public Mall() {
    }

    // Constructor complet
    public Mall(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    // GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getFloors() {
        return floors;
    }

    public void setFloors(List<String> floors) {
        this.floors = floors;
    }

    public void addFloorId(String floorId) {
        if (floorId != null && !floorId.isBlank()) {
            floors.add(floorId);
        }
    }

    public void removeFloorId(String floorId) {
        floors.removeIf(id -> id.equals(floorId));
    }

    public int getFloorCount() {
        return floors.size();
    }

    // IMPORTANT! Necesare pentru repo-ul tău
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mall)) return false;
        Mall mall = (Mall) o;
        return Objects.equals(id, mall.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
