package com.example.mall_management.model;

import java.util.Objects;

public class ElectricalAsset {

    private String id;          // <- necesar pentru repo-ul tău bazat pe ID string
    private String floorId;
    private Type type;
    private AssetStatus status;

    public enum Type {
        GENERATOR, TRANSFORMER, PANEL, UPS, OTHER
    }

    public enum AssetStatus {
        OPERATIONAL, MAINTENANCE, DOWN
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }



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
