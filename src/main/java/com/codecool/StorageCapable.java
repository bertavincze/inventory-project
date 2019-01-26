package com.codecool;

import java.util.List;

/**
 * A StorageCapable instance is able to retrieve all products as list of product instances ( List ).
 * Based on it the StoreManager can calculate the total price and display the name of the stored products.
 */

public interface StorageCapable {

    public List<Product> getAllProducts();

    public void storeCDProduct(String name, int price, int tracks);

    public void storeBookProduct(String name, int price, int pages);
}
