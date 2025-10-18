package com.example.mini_project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import com.example.mini_project.entity.product;

public interface productservice {
    String uploadCsv(MultipartFile file);

    Page<product> listProducts(Pageable pageable);

    Page<product> searchProducts(
        String brand,
        String color,
        Integer minPrice,
        Integer maxPrice,
        Pageable pageable
    );
}
 