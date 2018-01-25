package com.redhat.coolstore.model.impl;

import com.redhat.coolstore.model.ShoppingCart;
import com.redhat.coolstore.model.ShoppingCartItem;
import org.apache.commons.math3.util.Precision;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCartImpl implements Serializable, ShoppingCart {

	private static final long serialVersionUID = -1108043957592113528L;

	private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

	private String cartId;

	private double shippingTotal;

	private double cartItemPromoSavings;

    private double shippingPromoSavings;

    public ShoppingCartImpl() {

	}

	@Override
    public List<ShoppingCartItem> getShoppingCartItemList() {
		return shoppingCartItemList;
	}

	@Override
    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
		this.shoppingCartItemList = shoppingCartItemList;
	}

    @Override
    public String getCartId() {
        return cartId;
    }

    @Override
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }


	@Override
    public void addShoppingCartItem(ShoppingCartItem sci) {

		if ( sci != null ) {

			shoppingCartItemList.add(sci);

		}

	}


	@Override
    public double getCartItemTotal() {
		return Precision.round(this.getShoppingCartItemList().stream().mapToDouble(i -> i.getQuantity()*i.getProduct().getPrice()).sum(),2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
    public double getShippingTotal() {
		return shippingTotal;
	}


    @Override
    public void setShippingTotal(double shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

    @Override
    public double getCartTotal() {
		return Precision.round(this.getCartItemTotal()+this.getShippingTotal()-Math.abs(this.getShippingPromoSavings())-Math.abs(this.getCartItemPromoSavings()),2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
    public double getCartItemPromoSavings() {
        return cartItemPromoSavings;
    }

    @Override
    public void setCartItemPromoSavings(double cartItemPromoSavings) {
        this.cartItemPromoSavings = cartItemPromoSavings;
    }

    @Override
    public double getShippingPromoSavings() {
        return shippingPromoSavings;
    }

    @Override
    public void setShippingPromoSavings(double shippingPromoSavings) {
        this.shippingPromoSavings = shippingPromoSavings;
    }

    @Override
    public void clear() {
        shoppingCartItemList = new ArrayList<>();
        shippingTotal = 0;
        shippingPromoSavings = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartImpl that = (ShoppingCartImpl) o;
        return Double.compare(that.getCartItemTotal(), getCartItemTotal()) == 0 &&
            Double.compare(that.getCartItemPromoSavings(), getCartItemPromoSavings()) == 0 &&
            Double.compare(that.getShippingTotal(), getShippingTotal()) == 0 &&
            Double.compare(that.getShippingPromoSavings(), getShippingPromoSavings()) == 0 &&
            Double.compare(that.getCartTotal(), getCartTotal()) == 0 &&
            Objects.equals(getShoppingCartItemList(), that.getShoppingCartItemList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCartItemTotal(), getCartItemPromoSavings(), getShippingTotal(), getShippingPromoSavings(), getCartTotal(), getShoppingCartItemList());
    }

    @Override
    public String toString() {
        return "ShoppingCartImpl{" +
            "shoppingCartItemList=" + shoppingCartItemList +
            ", shippingTotal=" + this.getShippingTotal() +
            ", shippingPromoSavings=" + this.getShippingPromoSavings() +
            '}';
    }
}
