package com.contribe.arbetsprov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.expectThrows;

public class BookStoreQuantityTests {

    private BookStore store;
    private TestInventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new TestInventory();
        store = new BookStore(inventory);
    }

    @Test
    public void returnsTheCorrectStockQuantity() {
        givenInventoryContains(new BookID("1"), 4);
        thenTheStoreReportsQuantity(4, new BookID("1"));
    }

    @Test
    public void throwsExceptionIfBookIsUnknown() {
        givenInventoryContains(new BookID("1"), 4);
        expectThrows(NoSuchBookException.class, () ->
                store.getStockQuantity(new BookID("2")));
    }

    private void thenTheStoreReportsQuantity(int expected, BookID bookID) {
        assertEquals(expected, store.getStockQuantity(bookID));
    }

    private void givenInventoryContains(BookID bookID, int quantity) {
        inventory.stock.put(bookID, quantity);
    }
}
