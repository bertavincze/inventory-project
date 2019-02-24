package com.codecool;

import java.util.ArrayList;
import java.util.List;

public class CsvStore implements StorageCapable {

    private List<Product> products;

    public CsvStore() {
        this.products = loadProducts("products.csv");
    }

    public List<Product> loadProducts(String filename) {
        products = new ArrayList<>();
        FileHandler fh = new FileHandler();
        String[][] data = fh.read(filename);
        for (String[] strings : data) {
            String type = strings[3];
            if (type.equals("Book")) {
                storeBookProduct(strings[0], Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            } else if (type.equals("CD")) {
                storeCDProduct(strings[0], Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            }
        }
        return products;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    private void storeProduct(Product product) {
        if (product instanceof BookProduct) {
            storeBookProduct(product.getName(), product.getPrice(), ((BookProduct) product).getNumOfPages());
        } else if (product instanceof CDProduct) {
            storeCDProduct(product.getName(), product.getPrice(), ((CDProduct) product).getNumOfTracks());
        }
    }

    public void storeCDProduct(String name, int price, int tracks) {
        products.add(new CDProduct(name, price, tracks));
    }

    public void storeBookProduct(String name, int price, int pages) {
        products.add(new BookProduct(name, price, pages));
    }

    public void store(Product product) {
        storeProduct(product);
        saveToCsv(product);
    }

    private void saveToCsv(Product product) {
        FileHandler fh = new FileHandler();
        String productData = product.getName() + "," + product.getPrice() + "," + product.getSize() + "," + product.getType();
        fh.write("products.csv", productData);
    }
}
