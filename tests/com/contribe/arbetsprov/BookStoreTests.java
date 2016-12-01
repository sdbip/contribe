package com.contribe.arbetsprov;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookStoreTests {
    @Test
    public void returnsTheCorrectStockQuantity() {
        TestInventory inventory = new TestInventory();
        BookStore store = new BookStore(inventory);
        inventory.stock = new HashMap<BookID, Integer>() {{
            put(new BookID("1"), 4);
        }};

        assertEquals(4, store.getStockQuantity(new BookID("1")));
    }
}
