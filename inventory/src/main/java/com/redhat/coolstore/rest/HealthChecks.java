package com.redhat.coolstore.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.redhat.coolstore.service.InventoryService;
import org.wildfly.swarm.health.Health;
import org.wildfly.swarm.health.HealthStatus;

@Path("/infra")
public class HealthChecks {

    @Inject
    private InventoryService inventoryService;

    @GET
    @Health
    @Path("/health")
    public HealthStatus check() {

        if (inventoryService.isAlive()) {
            return HealthStatus.named("service-state").up();
        } else {
            return HealthStatus.named("service-state").down();
        }
    }
}

