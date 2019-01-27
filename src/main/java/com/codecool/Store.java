package com.codecool;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Store class is abstract. The store method accepts a Product instance as its parameter, which product is created
 * via the createProduct method. createProduct is implemented in the Store class and the concrete product creation
 * (BookProduct or CDProduct) depends on the type parameter. It can be "CD" or "Book".
 * The store method calls the saveToXml method and the storeProduct method. The store method implements a pattern
 * called the Strategy Pattern. It means the execution strategy (calling saveToXml and storeProduct ) is fixed even
 * if you inherit from this class.
 * The saveToXml method is implemented in the Store abstract class. Calling it saves the incoming product to XML.
 */

public abstract class Store implements StorageCapable {

    private void saveToXml(Product product) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element root = document.createElement("Products");
            document.appendChild(root);

            Element productDetails = document.createElement("Product");

            root.appendChild(productDetails);

            Attr name = document.createAttribute("name");
            name.setValue(product.getName());
            productDetails.setAttributeNode(name);
            // productDetails.setAttribute("name", product.getName());

            Attr price = document.createAttribute("price");
            price.setValue(String.valueOf(product.getPrice()));
            productDetails.setAttributeNode(price);
            // productDetails.setAttribute("price", product.getPrice());

            Attr size = document.createAttribute("size");
            size.setValue(String.valueOf(product.getSize()));
            productDetails.setAttributeNode(size);
            // productDetails.setAttribute("size", product.getSize());


            Attr type = document.createAttribute("type");
            type.setValue(product.getType());
            productDetails.setAttributeNode(type);
            // productDetails.setAttribute("type", product.getType());

            TransformerFactory trf = TransformerFactory.newInstance();
            Transformer transformer = trf.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("export.xml"));

            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    protected void storeProduct(Product product) {
    }

    protected Product createProduct(String type, String name, int price, int size) {
        switch (type) {
            case "CD":
                Product cdProduct = new CDProduct(name, price, size);
                return cdProduct;
            case "Book":
                Product bookProduct = new BookProduct(name, price, size);
                return bookProduct;
        }
        return null;
    }

    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        return products;
    }

    public void store(Product product) {
        storeProduct(product);
        saveToXml(product);
    }

}
