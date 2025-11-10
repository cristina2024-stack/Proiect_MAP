package com.example.mall_management.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Repository generic bazat pe JSON care respectă cerințele:
 * - citește/scrie liste de obiecte într-un fișier JSON;
 * - asigură unicitatea și validitatea ID-urilor (String);
 * - persistă automat după fiecare operație CRUD;
 * - forțează fișierele sub resources/data/;
 * - verifică ≥10 înregistrări la start.
 */
public class InFileRepository<T> implements AbstractRepository<T> {

    /** Adapter pentru a accesa ID-ul fără reflecție */
    public interface EntityAdapter<T> {
        String getId(T entity);
        void setId(T entity, String id);
        default void validate(T entity) {}
    }

    private static final Pattern VALID_ID = Pattern.compile("^[A-Za-z0-9_-]{1,128}$");

    private final ObjectMapper mapper;
    private final File jsonFile;
    private final EntityAdapter<T> adapter;


    protected final List<T> data = new ArrayList<>();

    public InFileRepository(String jsonPath, EntityAdapter<T> adapter) {
        this.adapter = Objects.requireNonNull(adapter, "adapter");
        ensureUnderResourcesData(jsonPath);
        this.jsonFile = new File(Objects.requireNonNull(jsonPath, "jsonPath")).getAbsoluteFile();
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        initStorage();
        loadFromDisk();
        ensureAtLeastTenRecords();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<T> findById(String id) {
        Objects.requireNonNull(id, "id");
        return data.stream().filter(e -> id.equals(adapter.getId(e))).findFirst();
    }

    @Override
    public T save(T entity) {
        Objects.requireNonNull(entity, "entity");
        adapter.validate(entity);

        String id = adapter.getId(entity);
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
            adapter.setId(entity, id);
        }
        ensureValidId(id);
        if (existsById(id)) {
            throw new IllegalArgumentException("Există deja entitate cu id='" + id + "'");
        }

        data.add(entity);
        persist();
        return entity;
    }


    public T update(T entity) {
        Objects.requireNonNull(entity, "entity");
        String id = adapter.getId(entity);
        if (id == null || id.isBlank()) {
            throw new NoSuchElementException("Nu pot face update: entitatea nu are ID setat");
        }
        return update(id, entity);
    }

    @Override
    public T update(String id, T entity) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(entity, "entity");

        ensureValidId(id);
        if (!existsById(id)) {
            throw new NoSuchElementException("Nu există entitate cu id='" + id + "'");
        }

        adapter.setId(entity, id);
        adapter.validate(entity);

        for (int i = 0; i < data.size(); i++) {
            if (id.equals(adapter.getId(data.get(i)))) {
                data.set(i, entity);
                persist();
                return entity;
            }
        }
        // fallback (nu ar trebui să ajungi aici)
        throw new NoSuchElementException("Nu există entitate cu id='" + id + "'");
    }


    public T saveOrUpdate(T entity) {
        Objects.requireNonNull(entity, "entity");
        adapter.validate(entity);

        String id = adapter.getId(entity);
        if (id == null || id.isBlank()) {
            // create
            id = UUID.randomUUID().toString();
            adapter.setId(entity, id);
            ensureValidId(id);
            data.add(entity);
            persist();
            return entity;
        }

        ensureValidId(id);
        int idx = indexOfId(id);
        if (idx >= 0) {
            // replace
            data.set(idx, entity);
            persist();
            return entity;
        } else {
            // create cu ID furnizat
            if (existsById(id)) {
                throw new IllegalArgumentException("Există deja entitate cu id='" + id + "'");
            }
            data.add(entity);
            persist();
            return entity;
        }
    }

    @Override
    public boolean deleteById(String id) {
        Objects.requireNonNull(id, "id");
        boolean removed = data.removeIf(e -> id.equals(adapter.getId(e)));
        if (removed) persist();
        return removed;
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public boolean existsById(String id) {
        Objects.requireNonNull(id, "id");
        return data.stream().anyMatch(e -> id.equals(adapter.getId(e)));
    }


    protected int indexOfId(String id) {
        for (int i = 0; i < data.size(); i++) {
            if (id.equals(adapter.getId(data.get(i)))) {
                return i;
            }
        }
        return -1;
    }


    protected void ensureValidId(String id) {
        if (!VALID_ID.matcher(id).matches()) {
            throw new IllegalArgumentException("ID invalid (1–128 caractere, [A-Za-z0-9_-])");
        }
    }

    private void ensureUnderResourcesData(String path) {
        String norm = path.replace("\\", "/");
        if (!norm.contains("resources/data/")) {
            throw new IllegalArgumentException("Fișierul JSON trebuie să fie sub 'resources/data/'");
        }
    }

    private void initStorage() {
        try {
            File dir = jsonFile.getParentFile();
            if (dir != null && !dir.exists()) {
                if (!dir.mkdirs() && !dir.exists()) {
                    throw new IOException("Nu pot crea directorul: " + dir);
                }
            }
            if (!jsonFile.exists()) {
                mapper.writeValue(jsonFile, Collections.<T>emptyList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Nu pot inițializa stocarea: " + jsonFile, e);
        }
    }

    private void loadFromDisk() {
        try {
            List<T> loaded = mapper.readValue(jsonFile, new TypeReference<List<T>>() {});
            Set<String> seen = new HashSet<>();
            for (T e : loaded) {
                String id = adapter.getId(e);
                if (id == null || id.isBlank()) {
                    throw new IllegalStateException("Entitate fără ID în " + jsonFile.getName());
                }
                ensureValidId(id);
                if (!seen.add(id)) {
                    throw new IllegalStateException("ID duplicat '" + id + "' în " + jsonFile.getName());
                }
            }
            data.clear();
            data.addAll(loaded);
        } catch (IOException e) {
            throw new RuntimeException("Nu pot citi JSON-ul: " + jsonFile.getName(), e);
        }
    }


    protected void persist() {
        try {
            File dir = jsonFile.getParentFile();
            if (dir == null) dir = new File(".");
            File tmp = File.createTempFile(jsonFile.getName(), ".tmp", dir);
            mapper.writeValue(tmp, data);
            Files.move(tmp.toPath(), jsonFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Nu pot salva JSON-ul: " + jsonFile.getName(), e);
        }
    }

    private void ensureAtLeastTenRecords() {
        if (data.size() < 10) {
            throw new IllegalStateException(
                    "Fișierul " + jsonFile.getName() + " trebuie să conțină cel puțin 10 înregistrări la start."
            );
        }
    }
}
