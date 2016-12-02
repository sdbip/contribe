package com.contribe.arbetsprov;

import java.util.HashMap;
import java.util.Map;

class TestInventory implements Inventory {
    Map<BookID, Integer> stock = new HashMap<>();

    @Override
    public int getStockQuantity(BookID bookID) {
        return stock.get(bookID);
    }

    @Override
    public void setStockQuantity(BookID bookID, int quantity) {
        stock.put(bookID, quantity);
    }

    @Override
    public boolean contains(BookID bookID) {
        return stock.containsKey(bookID);
    }

    @Override
    public void addBook(Book book, int quantity) {

    }
}
