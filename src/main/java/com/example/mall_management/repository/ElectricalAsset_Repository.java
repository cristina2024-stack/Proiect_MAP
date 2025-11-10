package com.example.mall_management.repository;

import com.example.mall_management.model.ElectricalAsset;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ElectricalAsset_Repository extends InFileRepository<ElectricalAsset> {

    public ElectricalAsset_Repository() {
        super(
                "src/main/resources/data/electrical_asset.json",
                new EntityAdapter<ElectricalAsset>() {
                    @Override
                    public String getId(ElectricalAsset asset) {
                        return asset.getId();
                    }

                    @Override
                    public void setId(ElectricalAsset asset, String id) {
                        asset.setId(id);
                    }

                    @Override
                    public void validate(ElectricalAsset asset) {
                    }
                }
        );
    }

    // ✅ Metode personalizate (acestea au voie să existe în repository)
    public ElectricalAsset findByFloorId(String floorId) {
        return findAll().stream()
                .filter(asset -> asset.getFloorId().equals(floorId))
                .findFirst()
                .orElse(null);
    }

    public ElectricalAsset findByType(ElectricalAsset.Type type) {
        return findAll().stream()
                .filter(asset -> asset.getType() == type)
                .findFirst()
                .orElse(null);
    }

    public List<ElectricalAsset> findAllByStatus(ElectricalAsset.AssetStatus status) {
        List<ElectricalAsset> result = new ArrayList<>();
        for (ElectricalAsset asset : findAll()) {
            if (asset.getStatus() == status) {
                result.add(asset);
            }
        }
        return result;
    }
}
