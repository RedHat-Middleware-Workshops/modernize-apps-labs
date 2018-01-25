package com.redhat.coolstore.model.impl;

import com.redhat.coolstore.model.Product;

import java.io.Serializable;
import java.util.Objects;

public class ProductImpl implements Product, Serializable {

    private static final long serialVersionUID = 1945410845813123205L;

    private String itemId;

    private String name;

    private String desc;

    private double price;

    private String location;

    private String link;


    public ProductImpl() {
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Product{" +
            "itemId='" + itemId + '\'' +
            ", name='" + name + '\'' +
            ", desc='" + desc + '\'' +
            ", price=" + price +
            ", location='" + location + '\'' +
            ", link='" + link + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImpl product = (ProductImpl) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
            Objects.equals(getItemId(), product.getItemId()) &&
            Objects.equals(getName(), product.getName()) &&
            Objects.equals(getDesc(), product.getDesc()) &&
            Objects.equals(getLocation(), product.getLocation()) &&
            Objects.equals(getLink(), product.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, desc, price, location, link);
    }
}
