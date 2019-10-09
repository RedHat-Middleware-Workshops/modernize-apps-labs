package com.redhat.coolstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redhat.coolstore.client.InventoryClient;
import com.redhat.coolstore.model.Product;

@Service
public class CatalogService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private InventoryClient inventoryClient;

    public Product read(String id) {
        Product product = this.repository.findById(id);
        product.setQuantity(this.inventoryClient.getInventoryStatus(product.getItemId()).getQuantity());
        return product;
    }

    public List<Product> readAll() {
        List<Product> productList = this.repository.readAll();
        productList.parallelStream()
                .forEach(p -> {
                    p.setQuantity(this.inventoryClient.getInventoryStatus(p.getItemId()).getQuantity());
                });
        return productList;
    }

}


