package com.codecool;

import java.util.List;

/**
 * The StoreManager to work correctly need to be provided with a StorageCapable instance via the addStorage method.
 * After this is done we can use it to add and store products. E.g. calling the addCDProduct method with the correct
 * arguments from the main method will store a CD product in the underlying storage facility.
 * In the implementation of the addCDProduct call the storeCDProduct method on the StorageCapable instance that was
 * previously added to the StoreManager.
 */
public class StoreManager {

    public void addStorage(StorageCapable storage) {

    }

    public void addCDProduct(String name, int price, int tracks) {
    }

    public void addBookProduct(String name, int price, int pages) {
    }

    public String listProducts() {

        return products;
    }

    public int getTotalProductPrice() {
        return totalPrice;
    }
}
