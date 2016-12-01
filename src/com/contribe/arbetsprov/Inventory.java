package com.contribe.arbetsprov;

interface Inventory {
    int getStockQuantity(BookID bookID);
    void setStockQuantity(BookID bookID, int quantity);
    boolean contains(BookID bookID);
}
