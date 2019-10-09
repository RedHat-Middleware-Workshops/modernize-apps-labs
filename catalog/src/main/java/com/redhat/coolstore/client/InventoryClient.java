package com.redhat.coolstore.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.redhat.coolstore.model.Inventory;
import feign.hystrix.FallbackFactory;

@FeignClient(name="inventory", fallbackFactory = InventoryClient.InventoryClientFallbackFactory.class)
public interface InventoryClient {
    @GetMapping(path = "/services/inventory/{itemId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Inventory getInventoryStatus(@PathVariable("itemId") String itemId);

    @Component
    class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {
        @Override
        public InventoryClient create(Throwable cause) {
            return itemId -> new Inventory(itemId,-1);
        }
    }
}
