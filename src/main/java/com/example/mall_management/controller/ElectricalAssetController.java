package com.example.mall_management.controller;

import com.example.mall_management.model.ElectricalAsset;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/electrical-assets")
public class ElectricalAssetController {
    private List<ElectricalAsset> assets = new ArrayList<>();

    @GetMapping
    public List<ElectricalAsset> getAllAssets() {
        return assets;
    }

    @GetMapping("/{id}")
    public Optional<ElectricalAsset> getAssetById(@PathVariable String id) {
        return assets.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    public ElectricalAsset addAsset(@RequestBody ElectricalAsset asset) {
        assets.add(asset);
        return asset;
    }

    @PutMapping("/{id}")
    public ElectricalAsset updateAsset(@PathVariable String id, @RequestBody ElectricalAsset updatedAsset) {
        for (int i = 0; i < assets.size(); i++) {
            if (assets.get(i).getId().equals(id)) {
                assets.set(i, updatedAsset);
                return updatedAsset;
            }
        }
        assets.add(updatedAsset);
        return updatedAsset;
    }

    @DeleteMapping("/{id}")
    public String deleteAsset(@PathVariable String id) {
        boolean removed = assets.removeIf(a -> a.getId().equals(id));
        return removed ? "Electrical asset deleted successfully." : "Asset not found.";
    }


    @GetMapping("/floor/{floorId}")
    public List<ElectricalAsset> getAssetsByFloor(@PathVariable String floorId) {
        List<ElectricalAsset> result = new ArrayList<>();
        for (ElectricalAsset a : assets) {
            if (a.getFloorId().equals(floorId)) {
                result.add(a);
            }
        }
        return result;
    }

    @GetMapping("/type/{type}")
    public List<ElectricalAsset> getAssetsByType(@PathVariable ElectricalAsset.Type type) {
        List<ElectricalAsset> result = new ArrayList<>();
        for (ElectricalAsset a : assets) {
            if (a.getType() == type) {
                result.add(a);
            }
        }
        return result;
    }

    @PatchMapping("/{id}/status")
    public String updateAssetStatus(@PathVariable String id, @RequestParam ElectricalAsset.AssetStatus status) {
        Optional<ElectricalAsset> assetOpt = assets.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

        if (assetOpt.isPresent()) {
            assetOpt.get().setStatus(status);
            return "Asset status updated successfully.";
        } else {
            return "Asset not found.";
        }
    }
}

