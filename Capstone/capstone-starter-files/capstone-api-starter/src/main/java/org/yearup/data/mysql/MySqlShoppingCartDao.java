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

    }

    @Override
    public void updateQuantity(int userId, int productId, int quantity) {

    }

    @Override
    public void clearCart(int userId) {

    }

    @Override
    public void removeProduct(int userId, int productId) {

    }
}