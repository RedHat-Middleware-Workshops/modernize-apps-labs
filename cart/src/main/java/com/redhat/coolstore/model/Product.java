package com.redhat.coolstore.model;


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

    String getLink();

    void setLink(String link);
}
