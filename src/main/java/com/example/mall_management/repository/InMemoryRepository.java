package com.example.mall_management.repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
//http://localhost:8080/shops
/**
 * Repository generic simplu, care stochează obiecte în memorie.
 * Este folosit ca bază pentru toate celelalte repository-uri.
 */
public class InMemoryRepository<T> {

    protected final List<T> items = new ArrayList<>();
    private final Class<T> type;

    public InMemoryRepository(Class<T> type) {
        this.type = type;
    }

    public void save(T item) {
        items.add(item);
    }

    public List<T> findAll() {
        return new ArrayList<>(items);
    }


    public void delete(T item) {
        items.remove(item);
    }


    public void update(T item) {
        if (items.contains(item)) {
            items.remove(item);
        }
        items.add(item);
    }


    public void clear() {
        items.clear();
    }

    public T findById(String id) {
        try {
            for (T item : items) {
                Method getId = item.getClass().getMethod("getId");
                Object value = getId.invoke(item);
                if (value != null && value.equals(id)) {
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteById(String id) {
        T item = findById(id);
        if (item != null) {
            delete(item);
        }
    }
}
