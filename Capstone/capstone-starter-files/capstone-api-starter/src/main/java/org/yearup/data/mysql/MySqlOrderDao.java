package org.yearup.data.mysql;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yearup.data.OrderDao;
import org.yearup.models.Order;
import org.yearup.models.ShippingInfo;

@Repository
public class MySqlOrderDao implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public MySqlOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order createOrder(Order order) {
        String sql = """
            INSERT INTO orders (user_id, date, address, city, state, zip, shipping_amount, total)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        ShippingInfo shippingInfo = order.getShippingInfo();

        jdbcTemplate.update(sql,
                order.getUserId(),
                java.sql.Timestamp.valueOf(order.getDate()),
                shippingInfo.getAddress(),
                shippingInfo.getCity(),
                shippingInfo.getState(),
                shippingInfo.getZip(),
                shippingInfo.getShippingAmount(),
                order.getTotal()
        );


        Integer orderId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        order.setOrderId(orderId);
        return order;
    }

    @Override
    public Order getOrderById(int orderId) {
        String sql = """
                
                SELECT order_id, user_id, date, address, city, state, zip, shipping_amount, total
                            FROM orders WHERE order_id = ?
                
                """;


        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ShippingInfo shippingInfo = new ShippingInfo(
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zip"),
                    rs.getBigDecimal("shipping_amount")
            );

            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setUserId(rs.getInt("user_id"));
            order.setDate(rs.getTimestamp("date").toLocalDateTime());
            order.setTotal(rs.getBigDecimal("total"));
            order.setShippingInfo(shippingInfo);

            return order;
        }, orderId);
    }
}
