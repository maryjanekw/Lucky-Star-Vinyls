package org.yearup.data;

import org.yearup.models.OrderLineItem;

import java.util.List;

public interface OrderLineItemDao {

    void addLineItem(OrderLineItem item);
    List<OrderLineItem> getLineItemsByOrderId(int orderId);

}
