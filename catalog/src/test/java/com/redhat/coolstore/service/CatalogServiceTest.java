package com.redhat.coolstore.service;

import com.redhat.coolstore.model.Inventory;
import com.redhat.coolstore.model.Product;
import io.specto.hoverfly.junit.dsl.HttpBodyConverter;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.HttpBodyConverter.json;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.serverError;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.startsWith;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CatalogServiceTest {

    @Autowired
    CatalogService catalogService;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(dsl(
            service("inventory:8080")
//                  .andDelay(500, TimeUnit.MILLISECONDS).forMethod("GET")
                    .get("/services/inventory/165613")
                        .willReturn(success(json(new Inventory("165613",13))))
                    .get("/services/inventory/165614")
                        .willReturn(success(json(new Inventory("165614",85))))
                    .get("/services/inventory/165954")
                        .willReturn(success(json(new Inventory("165954",78))))
                    .get("/services/inventory/329199")
                        .willReturn(success(json(new Inventory("329199",67))))
                    .get("/services/inventory/329299")
                        .willReturn(success(json(new Inventory("329299",98)))) //TODO: Replace with .willReturn(serverError())
                    .get("/services/inventory/444434")
                        .willReturn(success(json(new Inventory("444434",73))))
                    .get("/services/inventory/444435")
                        .willReturn(success(json(new Inventory("444435",64))))
                    .get("/services/inventory/444436")
                        .willReturn(success(json(new Inventory("444436",30))))

    ));

    @Test
    public void read() throws Exception {
        Product p1 = catalogService.read("444434");
        assertThat(p1).isNotNull();
        assertThat(p1.getName()).as("Verify product name").isEqualTo("Pebble Smart Watch");
        assertThat(p1.getQuantity()).as("Verify quantity").isEqualTo(73);

        Product p2 = catalogService.read("329299");
        assertThat(p2).isNotNull();
        assertThat(p2.getName()).as("Verify product name").isEqualTo("Red Fedora");
        assertThat(p2.getQuantity()).as("Verify quantity").isEqualTo(98); //TODO: Replace with Fallback value


    }

    @Test
    public void readAll() throws Exception {
        List<Product> productList = catalogService.readAll();
        assertThat(productList).isNotNull();
        assertThat(productList).isNotEmpty();
        List<String> names = productList.stream().map(Product::getName).collect(Collectors.toList());
        assertThat(names).contains("Red Fedora","Forge Laptop Sticker","Oculus Rift");
    }

}