package com.redhat.coolstore.service;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.redhat.coolstore.model.Inventory;

import java.util.Collection;
import java.util.List;

@Stateless
public class InventoryService {

    @PersistenceContext
    private EntityManager em;

    public InventoryService() {

    }

    public boolean isAlive() {
        return em.createQuery("select 1 from Inventory i")
                .setMaxResults(1)
                .getResultList().size() == 1;
    }
    public Inventory getInventory(String itemId) {
        return em.find(Inventory.class, itemId);
    }

    public List<Inventory> getAllInventory() {
        Query query = em.createQuery("SELECT i FROM Inventory i");
        return query.getResultList();
    }
}
