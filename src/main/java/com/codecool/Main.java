package com.codecool;


/**
 * The Main class is responsible for starting the application.
 * It creates a StoreManager instance to be able to store products.
 */

public class Main {

    public static void main(String[] args) {
        StoreManager storeManager = new StoreManager();
        //PersistentStore store = new PersistentStore();
        CsvStore store = new CsvStore();
        storeManager.addStorage(store);
        System.out.println(store.getAllProducts());
        store.store(new BookProduct("Example Book Part 2", 200, 250));
        System.out.println(store.getAllProducts());

    }
}
