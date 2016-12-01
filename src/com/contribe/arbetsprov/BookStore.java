package com.contribe.arbetsprov;

public class BookStore {
    private final Inventory inventory;

    public BookStore(Inventory inventory) {
        this.inventory = inventory;
    }

    int getStockQuantity(BookID bookID) {
        return inventory.getStockQuantity(bookID);
    }
}
