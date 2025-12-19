package org.yearup.data.mysql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.List;

@Repository
public class MySqlShoppingCartDao implements ShoppingCartDao {

    private final JdbcTemplate jdbcTemplate;

    public MySqlShoppingCartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public ShoppingCart getByUserId(int userId) {
        String sql = """
            SELECT p.product_id, p.name, p.price, p.description, p.category_id, 
                   p.sub_category, p.stock, p.image_url, p.featured, sc.quantity
            FROM shopping_cart sc
            JOIN products p ON sc.product_id = p.product_id
            WHERE sc.user_id = ?
        """;

        List<ShoppingCartItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("category_id"),
                    rs.getString("description"),
                    rs.getString("sub_category"),
                    rs.getInt("stock"),
                    rs.getBoolean("featured"),
                    rs.getString("image_url"));

            return new ShoppingCartItem(product, rs.getInt("quantity"));
        }, userId);

        ShoppingCart cart = new ShoppingCart();
        items.forEach(cart::add);
        return cart;
    }

    @Override
    public void addProduct(int userId, int productId) {
        String checkSql = "SELECT quantity FROM shopping_cart WHERE user_id =? AND product_id = ?";
        Integer existingQty = jdbcTemplate.query(checkSql, rs -> rs.next() ? rs.getInt("quantity"):
                null, userId, productId);

        if(existingQty != null) {
            String updateSQL = "UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";
            jdbcTemplate.update(updateSQL, userId, productId);
        }else{
            String insertSql = "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?, ?, 1)";
            jdbcTemplate.update(insertSql, userId, productId);
        }
    }

    @Override
    public void updateQuantity(int userId, int productId, int quantity) {
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, quantity, userId, productId);
    }

    @Override
    public void clearCart(int userId) {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void removeProduct(int userId, int productId) {
        String sql = "DELETE FROM shopping_cart WHERE user        String sql = "DELETE FROM shopping_cart WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, userId, productId);
    }
}