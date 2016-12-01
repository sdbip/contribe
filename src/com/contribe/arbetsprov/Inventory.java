package com.contribe.arbetsprov;

interface Inventory {
    int getStockQuantity(BookID bookID);
    boolean contains(BookID bookID);
}
