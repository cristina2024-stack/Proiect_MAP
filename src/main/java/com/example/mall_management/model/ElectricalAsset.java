package com.example.mall_management.model;


public class ElectricalAsset {
    public enum Type { LIFT, AC, LIGHT, ESCALATOR }
    public enum AssetStatus { WORKING, DOWN }

    private String id;
    private String floorId;
    private Type type;
    private AssetStatus status;

    public ElectricalAsset(String id, String floorId, Type type, AssetStatus status) {
        this.id = id;
        this.floorId = floorId;
        this.type = type;
        this.status = status;
    }

    public String getId() { return id; }
    public String getFloorId() { return floorId; }
    public Type getType() { return type; }
    public AssetStatus getStatus() { return status; }
    public void setStatus(AssetStatus status) { this.status = status; }
}
