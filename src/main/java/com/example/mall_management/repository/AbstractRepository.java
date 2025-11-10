package com.example.mall_management.repository;
import java.util.List;
import java.util.Optional;
public interface AbstractRepository<T> {
    List<T> findAll();
    Optional<T> findById(String id);
    T save(T entity);
    T update(String id, T entity);
    boolean deleteById(String id);
    long count();
    boolean existsById(String id);
}
