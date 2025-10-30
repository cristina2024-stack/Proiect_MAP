// src/main/java/com/example/mall_management/controller/MallController.java
package com.example.mall_management.controller;

import com.example.mall_management.model.Mall;
import com.example.mall_management.service.MallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/malls")
public class MallController {

    private final MallService mallService;

    // Constructor injection (recomandat)
    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    // Returnează toate mall-urile
    @GetMapping
    public List<Mall> getAllMalls() {
        return mallService.getAllMalls();
    }

    // Returnează un mall după ID
    @GetMapping("/{id}")
    public Mall getMallById(@PathVariable String id) {
        return mallService.getMallById(id);
    }

    // Creează un mall nou
    @PostMapping
    public Mall createMall(@RequestBody Mall mall) {
        return mallService.addMall(mall);
    }

    // Actualizează un mall existent
    @PutMapping("/{id}")
    public Mall updateMall(@PathVariable String id, @RequestBody Mall mall) {
        return mallService.updateMall(id, mall);
    }

    // Șterge un mall după ID
    @DeleteMapping("/{id}")
    public void deleteMall(@PathVariable String id) {
        mallService.deleteMall(id);
    }
}
