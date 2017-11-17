package com.redhat.coolstore.utils;

import com.redhat.coolstore.model.CatalogItemEntity;
import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.model.ProductImpl;

/**
 * Created by tqvarnst on 2017-03-30.
 */
public class Transformers {

    public static Product toProduct(CatalogItemEntity entity) {
        ProductImpl prod = new ProductImpl();
        prod.setItemId(entity.getItemId());
        prod.setName(entity.getName());
        prod.setDesc(entity.getDesc());
        prod.setPrice(entity.getPrice());
        prod.setLocation(entity.getInventory().getLocation());
        prod.setLink(entity.getInventory().getLink());
        prod.setQuantity(entity.getInventory().getQuantity());
        return prod;
    }
}
