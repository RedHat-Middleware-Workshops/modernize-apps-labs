package com.redhat.coolstore.model.impl;

import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.model.ShoppingCartItem;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingCartItemImpl implements Serializable, ShoppingCartItem {

	private static final long serialVersionUID = 6964558044240061049L;

	private int quantity;
	private Product product;

	public ShoppingCartItemImpl() {

	}


	@Override
    public Product getProduct() {
		return product;
	}

	@Override
    public void setProduct(Product product) {
		this.product = product;
	}

	@Override
    public int getQuantity() {
		return quantity;
	}

	@Override
    public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "ShoppingCartItem [quantity=" + quantity
                + ", product=" + product
				+ "]";
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItemImpl that = (ShoppingCartItemImpl) o;
        return quantity == that.quantity &&
            Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(quantity, product);
    }
}
