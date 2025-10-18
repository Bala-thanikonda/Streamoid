package com.example.mini_project.controller;
import com.example.mini_project.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.mini_project.service.productservice;


@Controller
@RequestMapping("/products")
public class productcontroller {


@Autowired
private productservice productService;


 @PostMapping("/upload")
 public String uploadProducts(@RequestParam("file") MultipartFile file, Model model) {
     String uploadResponse = productService.uploadCsv(file);
     model.addAttribute("uploadResponse", uploadResponse);
    return "redirect:/products";
}


 @GetMapping
 public String listProducts(
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "10") int size,
         @RequestParam(required = false) String brand,
         @RequestParam(required = false) String color,
         @RequestParam(required = false) Integer minPrice,
         @RequestParam(required = false) Integer maxPrice,
         Model model
 ) {
 // Trim and blank-to-null conversion for search fields
        if (brand != null) brand = brand.trim();
        if (color != null) color = color.trim();
        if (brand != null && brand.isBlank()) brand = null;
        if (color != null && color.isBlank()) color = null;


 Page<product> pageProducts = productService.searchProducts(
            brand, color, minPrice, maxPrice, PageRequest.of(page, size)
 );
        model.addAttribute("products", pageProducts.getContent());
        model.addAttribute("pageNumber", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("brand", brand);
        model.addAttribute("color", color);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "product";
}


 @GetMapping("/search")
 public String searchProducts(
         @RequestParam(required = false) String brand,
         @RequestParam(required = false) String color,
         @RequestParam(required = false) Integer minPrice,
         @RequestParam(required = false) Integer maxPrice,
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "10") int size,
         Model model
 ) {
     if (brand != null) brand = brand.trim();
     if (color != null) color = color.trim();
     if (brand != null && brand.isBlank()) brand = null;
    if (color != null && color.isBlank()) color = null;
     return listProducts(page, size, brand, color, minPrice, maxPrice, model);
 }
} 