package com.example.mall_management.service;

import com.example.mall_management.model.Mall;
import com.example.mall_management.repository.Mall_Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallService {

    private final Mall_Repository repository;

    public MallService(Mall_Repository repository) {
        this.repository = repository;
    }

    public Mall addMall(Mall mall) {
        repository.save(mall);
        return mall;
    }

    public Mall updateMall(String id, Mall mall) {
        mall.setId(id);
        repository.update(mall); // overload-ul din InFileRepository<T>
        return mall;
    }

    public void deleteMall(String id) {
        repository.deleteById(id);
    }

    // ✅ findById() -> Optional<Mall> : întoarcem Mall sau aruncăm excepție clară
    public Mall getMallById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mall not found: " + id));
    }

    public List<Mall> getAllMalls() {
        return repository.findAll();
    }

    public Mall getMallByName(String name) {
        return repository.findByName(name);
    }

    public List<Mall> getMallsByCity(String city) {
        return repository.findByCity(city);
    }

    public List<Mall> getMallsByMinimumFloorCount(int minFloors) {
        return repository.findByMinimumFloorCount(minFloors);
    }
}
