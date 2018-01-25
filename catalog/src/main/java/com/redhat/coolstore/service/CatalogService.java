package com.redhat.coolstore.service;

import com.redhat.coolstore.client.InventoryClient;
import com.redhat.coolstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    InventoryClient inventoryClient;

    public Product read(String id) {
        Product product = repository.findById(id);
        product.setQuantity(inventoryClient.getInventoryStatus(product.getItemId()).getQuantity());
        return product;
    }

    public List<Product> readAll() {
        List<Product> productList = repository.readAll();
        productList.parallelStream()
                .forEach(p -> {
                    p.setQuantity(inventoryClient.getInventoryStatus(p.getItemId()).getQuantity());
                });
        return productList;
    }

}


