// src/main/java/com/example/mall_management/repository/Floor_Repository.java
package com.example.mall_management.repository;

import com.example.mall_management.model.Floor;
import java.util.ArrayList;
import java.util.List;

public class Floor_Repository extends InMemoryRepository<Floor> {

    public Floor_Repository() {
        super(Floor.class);
    }


    public Floor findByNumber(int number) {
        List<Floor> allFloors = findAll();
        for (int i = 0; i < allFloors.size(); i++) {
            Floor f = allFloors.get(i);
            if (f.getNumber() == number) {
                return f;
            }
        }
        return null;
    }

    public List<Floor> findByShopCount(int shopCount) {
        List<Floor> result = new ArrayList<Floor>();
        List<Floor> allFloors = findAll();
        for (int i = 0; i < allFloors.size(); i++) {
            Floor f = allFloors.get(i);
            if (f.getShops() != null && f.getShops().size() == shopCount) {
                result.add(f);
            }
        }
        return result;
    }

    public List<Floor> findWithAtLeastShops(int minShops) {
        List<Floor> result = new ArrayList<Floor>();
        List<Floor> allFloors = findAll();
        for (int i = 0; i < allFloors.size(); i++) {
            Floor f = allFloors.get(i);
            int size = (f.getShops() == null) ? 0 : f.getShops().size();
            if (size >= minShops) {
                result.add(f);
            }
        }
        return result;
    }
}
