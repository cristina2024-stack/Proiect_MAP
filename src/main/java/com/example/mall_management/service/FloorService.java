package com.example.mall_management.service;

import com.example.mall_management.model.Floor;
import com.example.mall_management.repository.Floor_Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    private final Floor_Repository floorRepository;

    public FloorService(Floor_Repository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    public Optional<Floor> getFloorById(String id) {
        // findById din InFileRepository întoarce Floor sau null
        Floor floor = floorRepository.findById(id);
        return Optional.ofNullable(floor);
    }

    public Floor addFloor(Floor floor) {
        return floorRepository.save(floor);
    }

    public Floor updateFloor(String id, Floor updatedFloor) {
        // te asiguri că ID-ul rămâne cel corect
        updatedFloor.setId(id);
        // InFileRepository are update(T entity), nu update(id, entity)
        return floorRepository.update(updatedFloor);
    }

    public boolean deleteFloor(String id) {
        return floorRepository.deleteById(id);
    }

    // ---------------- RELAȚII (TOT ÎN SERVICE E OK!) ----------------

    public boolean addShopToFloor(String floorId, String shopId) {
        Floor floor = floorRepository.findById(floorId);
        if (floor == null) return false;

        floor.addShopId(shopId);      // metoda ta din Floor
        floorRepository.update(floor);
        return true;
    }

    public boolean addTaskToFloor(String floorId, String taskId) {
        Floor floor = floorRepository.findById(floorId);
        if (floor == null) return false;

        floor.addTaskId(taskId);
        floorRepository.update(floor);
        return true;
    }

    public boolean addElectricalToFloor(String floorId, String assetId) {
        Floor floor = floorRepository.findById(floorId);
        if (floor == null) return false;

        floor.addElectricalId(assetId);
        floorRepository.update(floor);
        return true;
    }

    public boolean addAssignmentToFloor(String floorId, String assignmentId) {
        Floor floor = floorRepository.findById(floorId);
        if (floor == null) return false;

        floor.addAssignmentId(assignmentId);
        floorRepository.update(floor);
        return true;
    }
}
