package com.example.mall_management.service;

import com.example.mall_management.model.ElectricalAsset;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ElectricalAssetService {
    private List<ElectricalAsset> assets = new ArrayList<>();

    public List<ElectricalAsset> getAllAssets() {
        return assets;
    }

    public Optional<ElectricalAsset> getAssetById(String id) {
        for (ElectricalAsset a : assets) {
            if (a.getId().equals(id)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public ElectricalAsset addAsset(ElectricalAsset asset) {
        assets.add(asset);
        return asset;
    }

    public ElectricalAsset updateAsset(String id, ElectricalAsset updatedAsset) {
        for (int i = 0; i < assets.size(); i++) {
            if (assets.get(i).getId().equals(id)) {
                assets.set(i, updatedAsset);
                return updatedAsset;
            }
        }
        assets.add(updatedAsset);
        return updatedAsset;
    }

    public boolean deleteAsset(String id) {
        for (int i = 0; i < assets.size(); i++) {
            if (assets.get(i).getId().equals(id)) {
                assets.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<ElectricalAsset> getAssetsByFloor(String floorId) {
        List<ElectricalAsset> result = new ArrayList<>();
        for (ElectricalAsset a : assets) {
            if (a.getFloorId().equals(floorId)) {
                result.add(a);
            }
        }
        return result;
    }

    public List<ElectricalAsset> getAssetsByType(ElectricalAsset.Type type) {
        List<ElectricalAsset> result = new ArrayList<>();
        for (ElectricalAsset a : assets) {
            if (a.getType() == type) {
                result.add(a);
            }
        }
        return result;
    }

    public boolean updateAssetStatus(String id, ElectricalAsset.AssetStatus newStatus) {
        for (ElectricalAsset a : assets) {
            if (a.getId().equals(id)) {
                a.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    public List<ElectricalAsset> getAssetsDown() {
        List<ElectricalAsset> result = new ArrayList<>();
        for (ElectricalAsset a : assets) {
            if (a.getStatus() == ElectricalAsset.AssetStatus.DOWN) {
                result.add(a);
            }
        }
        return result;
    }
}
