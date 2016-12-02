package com.contribe.arbetsprov;

import java.util.Set;

public interface Inventory {
    int getStockQuantity(BookID bookID);
    void setStockQuantity(BookID bookID, int quantity);
    boolean contains(BookID bookID);

    void addBook(Book book, int quantity);
    Set<Book> list(String searchString);
}
