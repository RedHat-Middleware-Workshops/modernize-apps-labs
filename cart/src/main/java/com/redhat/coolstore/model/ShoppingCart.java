package com.redhat.coolstore.model;

import java.util.List;

public interface ShoppingCart {
    List<ShoppingCartItem> getShoppingCartItemList();

    void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList);

    String getCartId();

    void setCartId(String cartId);

    void addShoppingCartItem(ShoppingCartItem sci);

    double getCartItemTotal();

    double getShippingTotal();

    void setShippingTotal(double shippingTotal);

    double getCartTotal();

    double getCartItemPromoSavings();

    void setCartItemPromoSavings(double cartItemPromoSavings);

    double getShippingPromoSavings();

    void setShippingPromoSavings(double shippingPromoSavings);

    void clear();



}
