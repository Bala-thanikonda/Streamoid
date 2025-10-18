package com.example.mini_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class product {
    @Id
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "brand", nullable = false)
    @NotBlank
    private String brand;

    @Column(name = "color", nullable = false)
    @NotBlank
    private String color;

    @Column(name = "size", nullable = false)
    @NotBlank
    private String size;

    @Column(name = "mrp", nullable = false)
    @Min(0)
    private int mrp;

    @Column(name = "price", nullable = false)
    @Min(0)
    private int price;

    @Column(name = "quantity", nullable = false)
    @Min(0)
    private int quantity;
}
