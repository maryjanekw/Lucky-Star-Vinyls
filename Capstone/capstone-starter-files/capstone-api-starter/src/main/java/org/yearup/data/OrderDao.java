package org.yearup.data;

import org.yearup.models.Order;

import java.math.BigDecimal;

public interface OrderDao {

    Order createOrder(Order order);
    Order getOrderById(int orderId);

}
