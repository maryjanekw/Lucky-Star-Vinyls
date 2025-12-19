package org.yearup.data.mysql;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yearup.data.OrderDao;
import org.yearup.models.Order;

@Repository
public class MySqlOrderDao implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public MySqlOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order createOrder(Order order) {
        String sql = """
            INSERT INTO orders (user_id, date, address, city, state, zip, shipping_amount)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                order.getUserId(),
                java.sql.Timestamp.valueOf(order.getDate()),
                order.getAddress(),
                order.getCity(),
                order.getState(),
                order.getZip(),
                order.getShippingAmount());

        Integer orderId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        order.setOrderId(orderId);
        return order;
    }

}
