package com.example.mini_project.service;


import com.example.mini_project.entity.product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.mini_project.repository.ProductRepository;

import java.io.InputStreamReader;

@Service
public class productserviceimpl implements productservice {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String uploadCsv(MultipartFile file) {
        int stored = 0, failed = 0;
        CSVFormat format = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .build();
        try (
            CSVParser csvParser = format.parse(new InputStreamReader(file.getInputStream()))
        ) {
            for (CSVRecord record : csvParser) {
                try {
                    product prod = new product(
                        record.get("sku").trim(),
                        record.get("name").trim(),
                        record.get("brand").trim(),
                        record.get("color").trim(),
                        record.get("size").trim(),
                        Integer.parseInt(record.get("mrp").trim()),
                        Integer.parseInt(record.get("price").trim()),
                        Integer.parseInt(record.get("quantity").trim())
                    );
                    // validation
                    if (prod.getPrice() > prod.getMrp() ||
                        prod.getQuantity() < 0 ||
                        prod.getSku() == null ||
                        prod.getName() == null ||
                        prod.getBrand() == null
                    ) {
                        failed++;
                        continue;
                    }
                    productRepository.save(prod);
                    stored++;
                } catch (Exception e) {
                    failed++;
                }
            }
        } catch(Exception e) {
            return "Failed to parse CSV";
        }
        return "stored: " + stored + ", failed: " + failed;
    }

    @Override
    public Page<product> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<product> searchProducts(
            String brand,
            String color,
            Integer minPrice,
            Integer maxPrice,
            Pageable pageable) {
        return productRepository.findByFilters(brand, color, minPrice, maxPrice, pageable);
    }
}
