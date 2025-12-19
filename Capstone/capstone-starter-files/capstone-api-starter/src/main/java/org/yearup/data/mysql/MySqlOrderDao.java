package org.yearup.data.mysql;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.yearup.data.OrderDao;
import org.yearup.models.Order;
import org.yearup.models.ShippingInfo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
public class MySqlOrderDao implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public MySqlOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order createOrder(Order order) {
        String sql = """
            INSERT INTO orders (user_id, date, shipping_amount)
                VALUES (?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getDate()));
            ps.setBigDecimal(3, order.getShippingAmount());

            return ps;

        }, keyHolder);

        order.setOrderId(keyHolder.getKey().intValue());
        System.out.println("DEBUG: Generated Order ID: " + order.getOrderId());

        return order;

    }

    @Override
    public Order getOrderById(int orderId) {
        String sql = """
                SELECT order_id, user_id, date, total
                            FROM orders 
                            WHERE order_id = ?
                """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setUserId(rs.getInt("user_id"));
            order.setDate(rs.getTimestamp("date").toLocalDateTime());
            order.setShippingAmount(rs.getBigDecimal("shipping_amount"));

            return order;
        }, orderId);
    }
}
