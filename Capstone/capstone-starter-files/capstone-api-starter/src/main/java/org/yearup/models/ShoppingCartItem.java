package org.yearup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class ShoppingCartItem {

    private Product product;
    private int quantity;
    private BigDecimal discountPercent;


    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.discountPercent = BigDecimal.ZERO;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    // Helper Methods
    @JsonIgnore
    public int getProductId() {
        return this.product.getProductId();
    }


    public void incrementQuantity() {
        this.quantity++;
    }

    public void applyDiscount(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getLineTotal() {
        BigDecimal basePrice = product.getPrice();
        BigDecimal quantity = new BigDecimal(this.quantity);

        BigDecimal subTotal = basePrice.multiply(quantity);
        BigDecimal discountAmount = subTotal.multiply(discountPercent);

        return subTotal.subtract(discountAmount);
    }

    @Override
    public String toString() {
        return String.format("%s (Qty: %d, Line Total: $%s)", product.getName(), quantity, getLineTotal());

    }
}
