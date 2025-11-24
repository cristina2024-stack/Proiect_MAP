package com.example.mall_management.model;

import java.util.Objects;

public class ElectricalAsset {

    private String id;
    private String floorId;
    private Type type;
    private AssetStatus status;

    public enum Type {
        GENERATOR, TRANSFORMER, PANEL, UPS, OTHER
    }

    public enum AssetStatus {
        OPERATIONAL, MAINTENANCE, DOWN
    }

    // === OBLIGATORIU pentru Jackson, Spring, Thymeleaf ===
    public ElectricalAsset() {
    }

    // (opțional) constructor complet
    public ElectricalAsset(String id, String floorId, Type type, AssetStatus status) {
        this.id = id;
        this.floorId = floorId;
        this.type = type;
        this.status = status;
    }

    // === GETTERS & SETTERS ===
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFloorId() { return floorId; }
    public void setFloorId(String floorId) { this.floorId = floorId; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public AssetStatus getStatus() { return status; }
    public void setStatus(AssetStatus status) { this.status = status; }

    // === EQUALS & HASHCODE doar pe ID ===
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricalAsset)) return false;
        ElectricalAsset that = (ElectricalAsset) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
