package com.contribe.arbetsprov;

import org.junit.jupiter.api.Test;

public class BookStoreTests {
    @Test
    public void returnsTheCorrectStockQuantity() {
        BookStore store = new BookStore(new TestInventory());
    }

    private class TestInventory implements Inventory {
    }
}
