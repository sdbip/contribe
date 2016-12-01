package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.BookID;
import com.contribe.arbetsprov.Inventory;

import java.util.HashMap;
import java.util.Map;

class InMemoryInventory implements Inventory {
    Map<BookID, Integer> stock;

    InMemoryInventory() {
        stock = new HashMap<>();
        stock.put(new BookID("0"), 0);
        stock.put(new BookID("1"), 1);
        stock.put(new BookID("2"), 2);
        stock.put(new BookID("3"), 3);
    }

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
}
