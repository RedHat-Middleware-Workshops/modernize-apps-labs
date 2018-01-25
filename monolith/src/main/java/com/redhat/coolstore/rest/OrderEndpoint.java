package com.redhat.coolstore.rest;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.coolstore.model.Order;
import com.redhat.coolstore.service.OrderService;

@RequestScoped
@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderEndpoint implements Serializable {

    private static final long serialVersionUID = -7227732980791688774L;

    @Inject
    private OrderService os;


    @GET
    @Path("/")
    public List<Order> listAll() {
        return os.getOrders();
    }

    @GET
    @Path("/{orderId}")
    public Order getOrder(@PathParam("orderId") long orderId) {
        return os.getOrderById(orderId);
    }

}
