package com.example.mini_project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.mini_project.entity.product;

@Repository
public interface ProductRepository extends JpaRepository<product, String> {

    // Flexible filter: works for any combination (null or set for each)
    @Query("SELECT p FROM product p WHERE " +
        "(:brand IS NULL OR LOWER(p.brand) = LOWER(:brand)) AND " +
        "(:color IS NULL OR LOWER(p.color) = LOWER(:color)) AND " +
        "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
        "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    Page<product> findByFilters(
        @Param("brand") String brand,
        @Param("color") String color,
        @Param("minPrice") Integer minPrice,
        @Param("maxPrice") Integer maxPrice,
        Pageable pageable
    );
}
