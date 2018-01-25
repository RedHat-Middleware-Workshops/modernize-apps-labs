package com.redhat.coolstore.utils;

import com.redhat.coolstore.model.*;
import com.redhat.coolstore.model.impl.ProductImpl;
import com.redhat.coolstore.model.impl.ShoppingCartImpl;
import com.redhat.coolstore.model.impl.ShoppingCartItemImpl;
import org.apache.commons.math3.util.Precision;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class Generator {

    private static final String[] PRODUCT_IDS = {"329299","329199","165613","165614","165954","444434","444435","444436"};
    private static final String[] PRODUCT_NAMES =  {"Red Fedora","Forge Laptop Sticker","Solid Performance Polo","Ogio Caliber Polo","16 oz. Vortex Tumbler","Pebble Smart Watch","Oculus Rift","Lytro Camera"};


    public static ShoppingCart generateShoppingCart(String cartId) {
        ShoppingCart cart = new ShoppingCartImpl();
        cart.setCartId(cartId);
        cart.setShippingTotal(round(ThreadLocalRandom.current().nextDouble(50, 100)));
        cart.setShippingPromoSavings(round(ThreadLocalRandom.current().nextDouble(0, 50)));
        int numberOfItem = ThreadLocalRandom.current().nextInt(1,9);
        IntStream.range(0,numberOfItem).forEach( i -> cart.addShoppingCartItem(generateItem(i)));

        return cart;


    }

    private static ShoppingCartItem generateItem(int index) {
        ShoppingCartItem item = new ShoppingCartItemImpl();
        item.setProduct(generateProduct(index));
        item.setQuantity(ThreadLocalRandom.current().nextInt(1,5));
        return item;
    }

    private static Product generateProduct(int index) {
        Product prod = new ProductImpl();
        prod.setItemId(PRODUCT_IDS[index]);
        prod.setName(PRODUCT_NAMES[index]);
        prod.setPrice(round(ThreadLocalRandom.current().nextDouble(10, 200)));
        return prod;
    }

    private static double round(double d) {
        return Precision.round(d,2, BigDecimal.ROUND_HALF_UP);
    }
}
