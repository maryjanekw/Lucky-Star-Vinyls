package org.yearup.data.mysql;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yearup.data.OrderLineItemDao;
import org.yearup.models.OrderLineItem;

import java.util.List;

@Repository
public class MySqlOrderLineItemDao implements OrderLineItemDao {

    private final JdbcTemplate jdbcTemplate;

    public MySqlOrderLineItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLineItem(OrderLineItem item) {
        String sql = """
                INSERT INTO order_line_items (order_id, product_id, sales_price, quantity, discount)
                            VALUES (?, ?, ?, ?, ?
                """;

        jdbcTemplate.update(sql,
                item.getOrderId(),
                item.getProductId(),
                item.getSalesPrice(),
                item.getQuantity(),
                item.getDiscount());
    }

    @Override
    public List<OrderLineItem> getLineItemsByOrderId(int orderId) {
        String sql = """
                SELECT order_line_item_id, order_id, product_id, sales_price, quantity, discount
                            FROM order_line_items WHERE order_id = ?
                """;
        return jdbcTemplate.query(sql,(rs, rowNum) -> {
            OrderLineItem lineItem = new OrderLineItem();

            lineItem.setOrderLineItemId(rs.getInt("order_line_item_id"));
            lineItem.setOrderId(rs.getInt("order_id"));
            lineItem.setProductId(rs.getInt("product_id"));

            lineItem.setSalesPrice(rs.getBigDecimal("sales_price"));
            lineItem.setQuantity(rs.getInt("quantity"));

            lineItem.setDiscount(rs.getBigDecimal("discount"));
            return lineItem;
        }, orderId);
    }
}
