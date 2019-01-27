package com.codecool;

import java.util.ArrayList;
import java.util.List;

/**
 * The Main class is responsible for starting the application.
 * It creates a StoreManager instance to be able to store products.
 */

public class Main {

    public static void main(String[] args) {
        StoreManager storeManager = new StoreManager();
        PersistentStore store = new PersistentStore();
        storeManager.addStorage(store);
        Product book1 = store.createProduct("Book", "Kutya", 100, 100);
        store.store(book1);


    }
}
