package com.contribe.arbetsprov;

import java.util.Collection;

public class BookStore {
    private final Inventory inventory;

    public BookStore(Inventory inventory) {
        this.inventory = inventory;
    }

    int getStockQuantity(BookID bookID) {
        if (!inventory.contains(bookID)) throw new NoSuchBookException();
        return inventory.getStockQuantity(bookID);
    }

    void buy(Collection<LineItem> books)
            throws NoSuchBookException, NotInStockException {
        for (LineItem lineItem : books) {
            requireStockQuantity(lineItem.bookID, lineItem.quantity);
        }
        for (LineItem lineItem : books) {
            buy(lineItem.bookID, lineItem.quantity);
        }
    }

    private void buy(BookID bookID, int quantity) {
        requireStockQuantity(bookID, quantity);
        int stockQuantity = getStockQuantity(bookID);
        stockQuantity -= quantity;
        inventory.setStockQuantity(bookID, stockQuantity);
    }

    private void requireStockQuantity(BookID bookID, int quantity) {
        int stockQuantity = getStockQuantity(bookID);
        if (stockQuantity < quantity) throw new NotInStockException();
    }

    final static class LineItem {
        final BookID bookID;
        final int quantity;

        LineItem(BookID bookID, int quantity) {
            this.bookID = bookID;
            this.quantity = quantity;
        }
    }
}
