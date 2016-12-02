package com.contribe.arbetsprov;

import java.math.BigDecimal;

public final class Book {
    public final BookID id;
    public final String title;
    public final String author;
    public final BigDecimal price;

    public Book(BookID id, String title, String author, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
