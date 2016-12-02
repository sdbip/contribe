package com.contribe.arbetsprov;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class TestInventory implements Inventory {
    Map<BookID, Integer> stock = new HashMap<>();
    Map<BookID, Book> books;

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

    @Override
    public Set<Book> list(String searchString) {
        Set<Book> result = new HashSet<>();
        for (Map.Entry<BookID, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            if (book.title.equals(searchString))
                result.add(book);
        }
        return result;
    }
}
