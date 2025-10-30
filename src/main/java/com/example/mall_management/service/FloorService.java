package com.example.mall_management.service;

import com.example.mall_management.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    private List<Floor> floors = new ArrayList<>();

    public List<Floor> getAllFloors() {
        return floors;
    }

    public Optional<Floor> getFloorById(String id) {
        for (Floor f : floors) {
            if (f.getId().equals(id)) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    public Floor addFloor(Floor floor) {
        floors.add(floor);
        return floor;
    }

    public Floor updateFloor(String id, Floor updatedFloor) {
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).getId().equals(id)) {
                floors.set(i, updatedFloor);
                return updatedFloor;
            }
        }
        floors.add(updatedFloor);
        return updatedFloor;
    }

    public boolean deleteFloor(String id) {
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).getId().equals(id)) {
                floors.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addShopToFloor(String floorId, Shop shop) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            floorOpt.get().addShop(shop);
            return true;
        }
        return false;
    }

    public boolean addTaskToFloor(String floorId, MaintenanceTask task) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            floorOpt.get().addTask(task);
            return true;
        }
        return false;
    }

    public boolean addElectricalToFloor(String floorId, ElectricalAsset asset) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            floorOpt.get().addElectrical(asset);
            return true;
        }
        return false;
    }

    public boolean addAssignmentToFloor(String floorId, StaffAssignment assignment) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            floorOpt.get().addAssignment(assignment);
            return true;
        }
        return false;
    }

    public List<Shop> getShopsOnFloor(String floorId) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            return floorOpt.get().getShops();
        }
        return new ArrayList<>();
    }

    public List<MaintenanceTask> getTasksOnFloor(String floorId) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            return floorOpt.get().getTasks();
        }
        return new ArrayList<>();
    }

    public List<ElectricalAsset> getElectricalsOnFloor(String floorId) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            return floorOpt.get().getElectricals();
        }
        return new ArrayList<>();
    }

    public List<StaffAssignment> getAssignmentsOnFloor(String floorId) {
        Optional<Floor> floorOpt = getFloorById(floorId);
        if (floorOpt.isPresent()) {
            return floorOpt.get().getAssignments();
        }
        return new ArrayList<>();
    }
}
