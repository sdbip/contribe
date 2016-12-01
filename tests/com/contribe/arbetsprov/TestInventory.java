package com.contribe.arbetsprov;

import java.util.Map;

class TestInventory implements Inventory {
    Map<BookID, Integer> stock;

    @Override
    public int getStockQuantity(BookID bookID) {
        return stock.get(bookID);
    }
}
