package com.contribe.arbetsprov;

public final class BookID {
    private final String isbn;

    public BookID(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BookID && isbn.equals(((BookID) obj).isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
