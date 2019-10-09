package com.redhat.coolstore.service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.coolstore.model.Product;

@RestController
@RequestMapping("/services")
public class CatalogEndpoint {
    private final CatalogService catalogService;

    public CatalogEndpoint(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/products")
    public List<Product> readAll() {
        return this.catalogService.readAll();
    }

    @GetMapping("/product/{id}")
    public Product read(@PathVariable("id") String id) {
        return this.catalogService.read(id);
    }
}
