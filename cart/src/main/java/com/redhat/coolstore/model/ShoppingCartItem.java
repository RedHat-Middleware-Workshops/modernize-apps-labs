package com.redhat.coolstore.model;

public interface ShoppingCartItem {

    Product getProduct();

    void setProduct(Product product);

    /**
     *
     * @return quantity of this product in the shopping cart
     */
    int getQuantity();

    /**
     * Sets the number of this product in the shopping cart
     * @param quantity the number of products of this type
     */
    void setQuantity(int quantity);

}
