package com.contribe.arbetsprov;

public class BookStore {
    private final Inventory inventory;

    public BookStore(Inventory inventory) {
        this.inventory = inventory;
    }

    int getStockQuantity(BookID bookID) {
        if (!inventory.contains(bookID)) throw new NoSuchBookException();
        return inventory.getStockQuantity(bookID);
    }

    void buy(BookID bookID, int quantity) {
        int stockQuantity = inventory.getStockQuantity(bookID);
        stockQuantity -= quantity;
        inventory.setStockQuantity(bookID, stockQuantity);
    }
}
