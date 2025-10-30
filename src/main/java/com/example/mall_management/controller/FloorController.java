package com.example.mall_management.controller;

import com.example.mall_management.model.Floor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/floors")
public class FloorController {

    // Simulăm o bază de date în memorie
    private List<Floor> floors = new ArrayList<>();

    @GetMapping
    public List<Floor> getAllFloors() {
        return floors;
    }

    @GetMapping("/{id}")
    public Optional<Floor> getFloorById(@PathVariable String id) {
        return floors.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }
    @PostMapping
    public Floor addFloor(@RequestBody Floor floor) {
        floors.add(floor);
        return floor;
    }
    @PutMapping("/{id}")
    public Floor updateFloor(@PathVariable String id, @RequestBody Floor updatedFloor) {
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).getId().equals(id)) {
                floors.set(i, updatedFloor);
                return updatedFloor;
            }
        }

        floors.add(updatedFloor);
        return updatedFloor;
    }
    @DeleteMapping("/{id}")
    public String deleteFloor(@PathVariable String id) {
        boolean removed = floors.removeIf(f -> f.getId().equals(id));
        return removed ? "Floor deleted successfully." : "Floor not found.";
    }
}

