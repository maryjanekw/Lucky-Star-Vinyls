package org.yearup.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.OrderDao;
import org.yearup.data.OrderLineItemDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrderDao orderDao;
    private final OrderLineItemDao orderLineItemDao;
    private final ShoppingCartDao shoppingCartDao;
    private final UserDao userDao;

    public OrdersController(OrderDao orderDao, OrderLineItemDao orderLineItemDao, ShoppingCartDao shoppingCartDao,
                            UserDao userDao) {
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> checkout(@RequestBody Map<String, String> shippingInfoMap,
                                                        Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated.");
        }

        String username = principal.getName();
        User user = userDao.getByUserName(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");
        }

        // Retrieve cart
        ShoppingCart cart = shoppingCartDao.getByUserId(user.getId());
        if (cart.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty.");
        }

        // Creating shipping information
        ShippingInfo shippingInfo = new ShippingInfo(
                shippingInfoMap.get("address"),
                shippingInfoMap.get("city"),
                shippingInfoMap.get("state"),
                shippingInfoMap.get("zip"),
                BigDecimal.valueOf(5.99) // shipping cost
        );

        // Create order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setDate(LocalDateTime.now());
        order.setShippingInfo(shippingInfo);

        order.setTotal(cart.getTotal().add(shippingInfo.getShippingAmount()));

        final Order finalOrder = order;

        // Add line items
        cart.getItems().values().forEach(item -> {
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setOrderId(finalOrder.getOrderId());
            lineItem.setProductId(item.getProductId());
            lineItem.setSalesPrice(item.getProduct().getPrice());
            lineItem.setQuantity(item.getQuantity());
            lineItem.setDiscount(BigDecimal.ZERO);
            orderLineItemDao.addLineItem(lineItem);

        });



        // Clear cart
        shoppingCartDao.clearCart(user.getId());

        // Prepares JSON response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order placed successfully");
        response.put("orderId", order.getOrderId());
        response.put("total", order.getTotal());
        response.put("shipping", shippingInfo.getShippingAmount());
        response.put("address", shippingInfo.getAddress());
        response.put("city", shippingInfo.getCity());
        response.put("state", shippingInfo.getState());
        response.put("zip", shippingInfo.getZip());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
