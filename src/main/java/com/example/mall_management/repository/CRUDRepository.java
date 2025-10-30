package com.example.mall_management.repository;

import java.util.List;

public interface CRUDRepository<T> {

    void save(T entity);
    void delete(String id);
    void update(T entity);
    T findById(String id);
    List<T> findAll();
}
