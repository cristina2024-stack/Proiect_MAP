package com.example.mall_management.repository;

import com.example.mall_management.model.Mall;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class Mall_Repository extends InFileRepository<Mall> {

    public Mall_Repository() {
        super(
                "src/main/resources/data/mall.json",
                new EntityAdapter<Mall>() {

                    @Override
                    public String getId(Mall m) {
                        return m.getId();
                    }

                    @Override
                    public void setId(Mall m, String id) {
                        m.setId(id); // asigură-te că Mall are setId(String)
                    }

                    @Override
                    public void validate(Mall m) {
                        // validări opționale
                        // if (m.getName() == null || m.getName().isBlank())
                        //     throw new IllegalArgumentException("Mall name cannot be empty");
                    }
                }
        );
    }

    // ----------------------- METODE CUSTOM -----------------------

    public Mall findByName(String name) {
        if (name == null) return null;

        for (Mall m : findAll()) {
            if (m.getName() != null && m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public List<Mall> findByCity(String city) {
        List<Mall> result = new ArrayList<>();
        if (city == null) return result;

        for (Mall m : findAll()) {
            if (m.getCity() != null && m.getCity().equalsIgnoreCase(city)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Mall> findByMinimumFloorCount(int minFloors) {
        List<Mall> result = new ArrayList<>();

        for (Mall m : findAll()) {
            if (m.getFloors() != null && m.getFloors().size() >= minFloors) {
                result.add(m);
            }
        }
        return result;
    }
}
