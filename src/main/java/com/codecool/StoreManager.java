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

    private StorageCapable storage;

    public void addStorage(StorageCapable storage) {
        this.storage = storage;
    }

    public void addCDProduct(String name, int price, int tracks) {
        storage.storeCDProduct(name, price, tracks);
    }

    public void addBookProduct(String name, int price, int pages) {
        storage.storeBookProduct(name, price, pages);
    }

    public List<Product> listProducts() {
        return storage.getAllProducts();
    }

    public int getTotalProductPrice() {
        int totalPrice = 0;
        for (Product product:storage.getAllProducts()) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
