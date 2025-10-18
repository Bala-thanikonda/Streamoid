package com.example.mini_project.controller;

import com.example.mini_project.entity.product;
import com.example.mini_project.service.productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    @Autowired
    private productservice productService;

    @PostMapping("/upload")
    public Map<String, Object> uploadProducts(@RequestParam("file") MultipartFile file) {
        String result = productService.uploadCsv(file);
        int stored = 0;
        int failed = 0;
        if (result.startsWith("stored:")) {
            try {
                String[] parts = result.replace("stored:", "").split(",");
                stored = Integer.parseInt(parts[0].trim());
                failed = Integer.parseInt(parts[1].replace("failed:", "").trim());
            } catch (Exception e) {}
        }
        Map<String, Object> response = new HashMap<>();
        response.put("stored", stored);
        response.put("failed", failed);
        return response;
    }

    @GetMapping
    public List<product> listProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size
    ) {
        return productService.listProducts(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/search")
    public List<product> searchProducts(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        if (brand != null) brand = brand.trim();
        if (color != null) color = color.trim();
        if (brand != null && brand.isBlank()) brand = null;
        if (color != null && color.isBlank()) color = null;
        return productService
                .searchProducts(brand, color, minPrice, maxPrice, PageRequest.of(page, size))
                .getContent();
    }
}
