package com.codecool;

/**
 * The Store class is abstract. The store method accepts a Product instance as its parameter, which product is created
 * via the createProduct method. createProduct is implemented in the Store class and the concrete product creation
 * (BookProduct or CDProduct) depends on the type parameter. It can be "CD" or "Book".
 * The store method calls the saveToXml method and the storeProduct method. The store method implements a pattern
 * called the Strategy Pattern. It means the execution strategy (calling saveToXml and storeProduct ) is fixed even
 * if you inherit from this class.
 * The saveToXml method is implemented in the Store abstract class. Calling it saves the incoming product to XML.
 */

public abstract class Store {
}
