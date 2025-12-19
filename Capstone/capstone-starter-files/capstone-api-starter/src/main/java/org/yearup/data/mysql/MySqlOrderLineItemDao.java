package org.yearup.data.mysql;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yearup.data.OrderLineItemDao;
import org.yearup.models.OrderLineItem;

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


}
