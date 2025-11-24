package com.example.mall_management.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JavaType;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Repo generic care salvează entități într-un fișier JSON.
 * Presupune că entitatea T are metodele:
 *   public String getId();
 *   public void setId(String id);
 */
public abstract class InFileRepository<T> {

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private final Path filePath;
    private final Class<T> clazz;

    /**
     * @param relativePath ex: "data/customer.json"
     *                     => fișierul va fi în: src/main/resources/data/customer.json
     */
    protected InFileRepository(String relativePath, Class<T> clazz) {
        this.clazz = clazz;
        this.filePath = Paths.get("src/main/resources").resolve(relativePath);
    }

    // ================== UTILITARE PRIVATE ==================

    private String extractId(T entity) {
        try {
            Method m = entity.getClass().getMethod("getId");
            Object value = m.invoke(entity);
            return value != null ? value.toString() : null;
        } catch (Exception e) {
            throw new RuntimeException("Entitatea " + entity.getClass().getSimpleName()
                    + " nu are metoda getId()", e);
        }
    }

    private void assignId(T entity, String entityId) {
        try {
            Method m = entity.getClass().getMethod("setId", String.class);
            m.invoke(entity, entityId);
        } catch (Exception e) {
            throw new RuntimeException("Entitatea " + entity.getClass().getSimpleName()
                    + " nu are metoda setId(String)", e);
        }
    }

    // ================== OPERATII DE BAZĂ PE FIȘIER ==================

    public synchronized List<T> findAll() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            byte[] bytes = Files.readAllBytes(filePath);

            if (bytes.length == 0) {
                return new ArrayList<>();
            }

            // folosim clazz ca să îi spunem lui Jackson că vrem List<T>, nu List<LinkedHashMap>
            JavaType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);

            return objectMapper.readValue(bytes, listType);

        } catch (IOException e) {
            System.err.println("Nu pot citi fișierul " + filePath + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    protected synchronized void saveAll(List<T> items) {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }

            objectMapper.writeValue(filePath.toFile(), items);

        } catch (IOException e) {
            throw new RuntimeException("Nu pot scrie în fișierul " + filePath, e);
        }
    }

    // ================== CRUD GENERIC ==================

    public synchronized T findById(String entityId) {
        if (entityId == null) return null;

        List<T> all = findAll();
        for (T entity : all) {
            String currentId = extractId(entity);
            if (entityId.equals(currentId)) {
                return entity;
            }
        }
        return null;
    }

    public synchronized T save(T entity) {
        List<T> all = new ArrayList<>(findAll());

        // dacă nu are ID, generăm unul nou
        String entityId = extractId(entity);
        if (entityId == null || entityId.isBlank()) {
            entityId = UUID.randomUUID().toString();
            assignId(entity, entityId);
        }

        // căutăm dacă există deja un element cu acest ID
        int indexToReplace = -1;
        for (int i = 0; i < all.size(); i++) {
            T current = all.get(i);
            String currentId = extractId(current);
            if (entityId.equals(currentId)) {
                indexToReplace = i;
                break;
            }
        }

        if (indexToReplace >= 0) {
            // există deja -> îl înlocuim
            all.set(indexToReplace, entity);
        } else {
            // nu există -> îl adăugăm
            all.add(entity);
        }

        saveAll(all);
        return entity;
    }

    public synchronized T update(T entity) {
        String entityId = extractId(entity);
        if (entityId == null || entityId.isBlank()) {
            throw new IllegalArgumentException("Nu pot face update pe o entitate fără ID");
        }

        List<T> all = new ArrayList<>(findAll());
        boolean found = false;

        for (int i = 0; i < all.size(); i++) {
            T current = all.get(i);
            String currentId = extractId(current);

            if (entityId.equals(currentId)) {
                all.set(i, entity);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NoSuchElementException("Nu există entitate cu id=" + entityId);
        }

        saveAll(all);
        return entity;
    }

    public synchronized T saveOrUpdate(T entity) {
        String entityId = extractId(entity);
        if (entityId == null || entityId.isBlank()) {
            // nu are ID -> considerăm create
            return save(entity);
        } else {
            // are ID, vedem dacă există deja
            T existing = findById(entityId);
            if (existing != null) {
                return update(entity);
            } else {
                return save(entity);
            }
        }
    }

    public synchronized boolean deleteById(String entityId) {
        if (entityId == null) return false;

        List<T> all = new ArrayList<>(findAll());
        boolean changed = false;

        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            T current = it.next();
            String currentId = extractId(current);

            if (entityId.equals(currentId)) {
                it.remove();
                changed = true;
            }
        }

        if (changed) {
            saveAll(all);
        }

        return changed;
    }
}
