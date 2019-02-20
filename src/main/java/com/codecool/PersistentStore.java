package com.codecool;

import java.util.List;

/**
 * The PersistentStore extends the Store class and implements the storeProduct method.
 * When called the incoming product is stored in memory (in a list, or array for example).
 */

public class PersistentStore extends Store {

    private List<Product> products;

    public PersistentStore() {
        this.products = super.loadProducts("products.xml");
    }

    @Override
    public void storeProduct(Product product) {
        if (product instanceof BookProduct) {
            storeBookProduct(product.getName(), product.getPrice(), ((BookProduct) product).getNumOfPages());
        } else if (product instanceof CDProduct) {
            storeCDProduct(product.getName(), product.getPrice(), ((CDProduct) product).getNumOfTracks());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

}
