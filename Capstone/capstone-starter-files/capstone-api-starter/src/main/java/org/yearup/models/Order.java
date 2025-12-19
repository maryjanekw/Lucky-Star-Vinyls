package org.yearup.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private BigDecimal total;
    private LocalDateTime orderDate;
    private List<OrderLineItem> lineItems = new ArrayList<>();


    public Order(int orderId, int userId, BigDecimal total, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.total = total;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}