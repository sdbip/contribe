package com.contribe.arbetsprov;

public interface Inventory {
    int getStockQuantity(BookID bookID);
    void setStockQuantity(BookID bookID, int quantity);
    boolean contains(BookID bookID);

    void addBook(Book book, int quantity);
}
