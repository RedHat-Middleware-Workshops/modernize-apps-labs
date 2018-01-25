package com.redhat.coolstore.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.model.ShoppingCart;
import com.redhat.coolstore.model.ShoppingCartItem;
import com.redhat.coolstore.utils.Transformers;

@Stateful
public class ShoppingCartService {

    @Inject
    Logger log;

    @Inject
    ProductService productServices;

    @Inject
    ShippingService ss;

    @Inject
    PromoService ps;

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/topic/orders")
    private Topic ordersTopic;

    private Map<String, ShoppingCart> carts = new HashMap<>(); //Each user can have multiple shopping carts (tabbed browsing)

    public ShoppingCartService() {
    }

    public ShoppingCart getShoppingCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            ShoppingCart c = new ShoppingCart();
            carts.put(cartId, c);
            return c;
        } else {
            return carts.get(cartId);
        }
    }

    public ShoppingCart checkOutShoppingCart(String cartId) {
        ShoppingCart cart = this.getShoppingCart(cartId);
        context.createProducer().send(ordersTopic, Transformers.shoppingCartToJson(cart));
        cart.resetShoppingCartItemList();
        priceShoppingCart(cart);
        return cart;
    }

    public void priceShoppingCart(ShoppingCart sc) {

        if (sc != null) {

            initShoppingCartForPricing(sc);

            if (sc.getShoppingCartItemList() != null && sc.getShoppingCartItemList().size() > 0) {

                ps.applyCartItemPromotions(sc);

                for (ShoppingCartItem sci : sc.getShoppingCartItemList()) {

                    sc.setCartItemPromoSavings(
                            sc.getCartItemPromoSavings() + sci.getPromoSavings() * sci.getQuantity());
                    sc.setCartItemTotal(sc.getCartItemTotal() + sci.getPrice() * sci.getQuantity());

                }

                ss.calculateShipping(sc);

            }

            ps.applyShippingPromotions(sc);

            sc.setCartTotal(sc.getCartItemTotal() + sc.getShippingTotal());

        }

    }

    private void initShoppingCartForPricing(ShoppingCart sc) {

        sc.setCartItemTotal(0);
        sc.setCartItemPromoSavings(0);
        sc.setShippingTotal(0);
        sc.setShippingPromoSavings(0);
        sc.setCartTotal(0);

        for (ShoppingCartItem sci : sc.getShoppingCartItemList()) {
            Product p = getProduct(sci.getProduct().getItemId());
            //if product exist
            if (p != null) {
                sci.setProduct(p);
                sci.setPrice(p.getPrice());
            }

            sci.setPromoSavings(0);
        }

    }

    public Product getProduct(String itemId) {
        return productServices.getProductByItemId(itemId);
    }

}
