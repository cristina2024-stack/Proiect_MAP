package com.example.mall_management.repository;

import com.example.mall_management.model.Mall;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Primary
@Repository
public class Mall_Repository extends InMemoryRepository<Mall> {

    public Mall_Repository() {
        super(Mall.class);
    }

    public Mall findByName(String name) {
        List<Mall> malls = findAll();
        for (int i = 0; i < malls.size(); i++) {
            Mall m = malls.get(i);
            if (m.getName() != null && m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public List<Mall> findByCity(String city) {
        List<Mall> result = new ArrayList<Mall>();
        List<Mall> malls = findAll();
        for (int i = 0; i < malls.size(); i++) {
            Mall m = malls.get(i);
            if (m.getCity() != null && m.getCity().equalsIgnoreCase(city)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Mall> findByMinimumFloorCount(int minFloors) {
        List<Mall> result = new ArrayList<Mall>();
        List<Mall> malls = findAll();
        for (int i = 0; i < malls.size(); i++) {
            Mall m = malls.get(i);
            if (m.getFloors() != null && m.getFloors().size() >= minFloors) {
                result.add(m);
            }
        }
        return result;
    }
}
