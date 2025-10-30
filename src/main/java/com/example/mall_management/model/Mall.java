package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;

public class Mall {

    private String id;
    private String name;
    private String city;
    private List<Floor> floors = new ArrayList<>();


    public Mall() {
    }

    public Mall(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

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

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor) {
        if (floor != null) {
            floors.add(floor);
        }
    }

    public void removeFloorById(String floorId) {
        floors.removeIf(f -> f.getId().equals(floorId));
    }

    public int getFloorCount() {
        return floors.size();
    }
}
