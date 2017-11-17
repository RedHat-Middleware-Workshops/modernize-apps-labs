package com.redhat.coolstore.model;

/**
 * Created by tqvarnst on 2017-03-30.
 */
public interface Product {
    String getItemId();

    void setItemId(String itemId);

    String getName();

    void setName(String name);

    String getDesc();

    void setDesc(String desc);

    double getPrice();

    void setPrice(double price);

    String getLocation();

    void setLocation(String location);

    int getQuantity();

    void setQuantity(int quantity);

    String getLink();

    void setLink(String link);
}
