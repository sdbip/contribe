package com.contribe.arbetsprov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.expectThrows;

public class BookStoreBuyTests {

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
        whenBuyingBook(new BookID("1"), 2);
        thenTheStoreReportsQuantity(2, new BookID("1"));
    }

    @Test
    public void throwsExceptionIfBookIsUnknown() {
        givenInventoryContains(new BookID("1"), 4);
        expectThrows(NoSuchBookException.class, () ->
                store.buy(new BookID("2"), 1));
    }

    private void thenTheStoreReportsQuantity(int expected, BookID bookID) {
        assertEquals(expected, store.getStockQuantity(bookID));
    }

    private void givenInventoryContains(BookID bookID, int quantity) {
        inventory.stock.put(bookID, quantity);
    }

    private void whenBuyingBook(BookID bookID, int quantity) {
        store.buy(bookID, quantity);
    }
}
