package com.codecool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    protected List<Product> products = new ArrayList<>();

    private void saveToXml(Product product) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element root = document.createElement("Products");
            document.appendChild(root);

            Element productDetails = document.createElement("Product");

            root.appendChild(productDetails);

            document.createAttribute("name");
            productDetails.setAttribute("name", product.getName());

            document.createAttribute("price");
            productDetails.setAttribute("price", String.valueOf(product.getPrice()));

            document.createAttribute("size");
            productDetails.setAttribute("size", (String.valueOf(product.getSize())));

            document.createAttribute("type");
            productDetails.setAttribute("type", product.getType());

            TransformerFactory trf = TransformerFactory.newInstance();
            Transformer transformer = trf.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("products.xml"));

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {
        products.add(new CDProduct(name, price, tracks));
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        products.add(new BookProduct(name, price, pages));
    }

    protected void storeProduct(Product product) {
        if (product instanceof BookProduct) {
            storeBookProduct(product.getName(), product.getPrice(), ((BookProduct) product).getNumOfPages());
        } else if (product instanceof CDProduct) {
            storeCDProduct(product.getName(), product.getPrice(), ((CDProduct) product).getNumOfTracks());
        }
    }

    protected Product createProduct(String type, String name, int price, int size) {
        switch (type) {
            case "CD":
                return new CDProduct(name, price, size);
            case "Book":
                return new BookProduct(name, price, size);
        }
        return null;
    }

    public List<Product> loadProducts(String xmlPath) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream is = new FileInputStream(xmlPath);
            document = db.parse(is);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList nodeList = document.getElementsByTagName("Product");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node tempNode = nodeList.item(i);
            Element tempElement = (Element) tempNode;
            String type = tempElement.getAttribute("type");
            products.add(createProduct(type, tempElement.getAttribute("name"),
                        Integer.parseInt(tempElement.getAttribute("price")),
                        Integer.parseInt(tempElement.getAttribute("size"))));
        }
        return products;
    }

    public void store(Product product) {
        storeProduct(product);
        saveToXml(product);
    }

}
