// src/main/java/com/example/mall_management/repository/ElectricalAssetRepository.java
package com.example.mall_management.repository;

import com.example.mall_management.model.ElectricalAsset;
import java.util.ArrayList;
import java.util.List;

public class ElectricalAsset_Repository extends InMemoryRepository<ElectricalAsset> {

    public ElectricalAsset_Repository() {
        super(ElectricalAsset.class);
    }


    public ElectricalAsset findByFloorId(String floorId) {
        List<ElectricalAsset> allAssets = findAll();
        for (ElectricalAsset asset : allAssets) {
            if (asset.getFloorId().equals(floorId)) {
                return asset;
            }
        }
        return null;
    }

    public ElectricalAsset findByType(ElectricalAsset.Type type) {
        List<ElectricalAsset> allAssets = findAll();
        for (ElectricalAsset asset : allAssets) {
            if (asset.getType() == type) {
                return asset;
            }
        }
        return null;
    }

    public List<ElectricalAsset> findAllByStatus(ElectricalAsset.AssetStatus status) {
        List<ElectricalAsset> result = new ArrayList<>();
        List<ElectricalAsset> allAssets = findAll();
        for (ElectricalAsset asset : allAssets) {
            if (asset.getStatus() == status) {
                result.add(asset);
            }
        }
        return result;
    }
}
