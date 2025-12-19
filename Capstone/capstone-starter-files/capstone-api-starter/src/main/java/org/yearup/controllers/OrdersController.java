package org.yearup.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Map<String, Object>> checkout(Principal principal) {
        System.out.println("=== Checkout process started ===");

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated.");
        }

//        String username = principal.getName();
        User user = userDao.getByUserName(principal.getName());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");
        }

        ShoppingCart cart = shoppingCartDao.getByUserId(user.getId());
        if (cart.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty.");
        }

        // Create order
        Order newOrder = new Order();
        newOrder.setUserId(user.getId());
        newOrder.setDate(LocalDateTime.now());
        newOrder.setShippingAmount(BigDecimal.ZERO);

        System.out.println("DEBUG: Persisting order...");
        final Order order = orderDao.createOrder(newOrder);
        System.out.println("DEBUG: Order persisted with ID: " + order.getOrderId());

        // Add line items
        cart.getItems().values().forEach(item -> {
            System.out.println("DEBUG: Adding line item for product ID: " + item.getProductId());

            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setOrderId(order.getOrderId());
            lineItem.setProductId(item.getProductId());
            lineItem.setSalesPrice(item.getProduct().getPrice());
            lineItem.setQuantity(item.getQuantity());
            lineItem.setDiscount(BigDecimal.ZERO);

            orderLineItemDao.addLineItem(lineItem);
        });

        shoppingCartDao.clearCart(user.getId());
        System.out.println("DEBUG: Cart cleared for user ID: " + user.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order placed successfully");
        response.put("orderId", order.getOrderId());
//        response.put("total", order.getTotal());

        System.out.println("=== Checkout process completed successfully ===");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
