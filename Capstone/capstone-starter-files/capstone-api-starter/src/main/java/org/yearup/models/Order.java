package org.yearup.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private int userId;
    private LocalDateTime date;
    private BigDecimal total;
    private ShippingInfo shippingInfo;
    private List<OrderLineItem> lineItems = new ArrayList<>();



    public Order() {}

    public Order(int orderId, int userId, LocalDateTime date, BigDecimal total, ShippingInfo shippingInfo) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.total = total;
        this.shippingInfo = shippingInfo;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}