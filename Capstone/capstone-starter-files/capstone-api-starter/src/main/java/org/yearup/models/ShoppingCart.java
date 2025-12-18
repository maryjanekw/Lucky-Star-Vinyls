package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    public Map<Integer, ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, ShoppingCartItem> items) {
        this.items = items;
    }

    public boolean contains(int productId) {
        return items.containsKey(productId);
    }

    public void add(ShoppingCartItem item) {
        items.put(item.getProductId(), item);
    }

    public ShoppingCartItem get(int productId) {
        return items.get(productId);
    }

    public void remove(int productId) {
        items.remove(productId);
    }

    public void updateQuantity(int productId, int quantity){
        if(items.containsKey(productId)){
            ShoppingCartItem item = items.get(productId);
            item.setQuantity(quantity);
        }
    }

    public void incrementQuantity(int productId){
        if(items.containsKey(productId)){
            ShoppingCartItem item = items.get(productId);
            item.setQuantity(item.getQuantity() + 1);
        }
    }


    public BigDecimal getTotal() {
        BigDecimal total = items.values()
                                .stream()
                                .map(i -> i.getLineTotal())
                                .reduce( BigDecimal.ZERO, (lineTotal, subTotal) -> subTotal.add(lineTotal));

        return total;
    }

}
