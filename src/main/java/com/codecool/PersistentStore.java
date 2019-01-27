package com.codecool;

import java.util.List;

/**
 * The PersistentStore extends the Store class and implements the storeProduct method.
 * When called the incoming product is stored in memory (in a list, or array for example).
 */

public class PersistentStore extends Store {

    @Override
    public void storeProduct(Product product) {
        List<Product> products = super.loadProducts();
        products.add(product);
    }

}
