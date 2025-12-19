package org.yearup.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.data.OrderDao;
import org.yearup.data.OrderLineItemDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;

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


}
