package com.codecool;

import java.util.List;

/**
 * The PersistentStore extends the Store class and implements the storeProduct method.
 * When called the incoming product is stored in memory (in a list, or array for example).
 */

public class PersistentStore extends Store {

    private List<Product> products;

    @Override
    public void storeProduct(Product product) {
        this.products = super.loadProducts();
        products.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.products;
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {

    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {

    }
}
