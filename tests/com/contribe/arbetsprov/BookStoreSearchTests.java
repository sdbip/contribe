package com.contribe.arbetsprov;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookStoreSearchTests {
    @Test
    public void findsBookByCompleteTitle() {
        TestInventory inventory = new TestInventory();
        BookStore store = new BookStore(inventory);
        inventory.books = new HashMap<BookID, Book>() {{
            put(new BookID("1"), new Book(new BookID("1"), "Some Title", "", null));
        }};
        Set<Book> foundBooks = store.list("Some Title");
        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());
        Book book = foundBooks.iterator().next();
        assertEquals("Some Title", book.title);
    }
}
