package com.example.mall_management.repository;

import com.example.mall_management.model.Mall;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class Mall_Repository extends InFileRepository<Mall> {

    public Mall_Repository() {
        super(
                "src/main/resources/data/mall.json",   // calea fișierului
                Mall.class                            // tipul entității
        );
    }

    public Mall findByName(String name) {
        if (name == null) return null;
        for (Mall m : findAll()) {
            if (m.getName() != null &&
                    m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public List<Mall> findByCity(String city) {
        List<Mall> result = new ArrayList<>();
        if (city == null) return result;

        for (Mall m : findAll()) {
            if (m.getCity() != null &&
                    m.getCity().equalsIgnoreCase(city)) {
                result.add(m);
            }
        }
        return result;
    }
    public List<Mall> findByMinimumFloorCount(int minFloors) {
        List<Mall> result = new ArrayList<>();
        for (Mall m : findAll()) {
            if (m.getFloorCount() >= minFloors) {
                result.add(m);
            }
        }
        return result;
    }
}
