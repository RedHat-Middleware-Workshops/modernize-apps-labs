package com.redhat.coolstore.model;

import java.io.Serializable;

public class Inventory implements Serializable {

    private static final long serialVersionUID = 7131670354907280071L;

    private String itemId;
    private String location;
    private int quantity;
    private String link;

    public Inventory() {
    }

    public Inventory(String itemId, String location, int quantity, String link) {
        this.itemId = itemId;
        this.location = location;
        this.quantity = quantity;
        this.link = link;
    }

    public Inventory(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "itemId='" + itemId + '\'' +
                ", location='" + location + '\'' +
                ", quantity=" + quantity +
                ", link='" + link + '\'' +
                '}';
    }
}
