package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.BookID;
import com.contribe.arbetsprov.BookStore;

import java.text.ParseException;

class LookupQuantityCommand implements Command {
    private BookID bookID;

    @Override
    public void parse(String[] args) throws ParseException {
        // Main lookup-quantity bookID

        if (args.length != 3) throw new ParseException("", 0);
        bookID = new BookID(args[2]);
    }

    @Override
    public void execute() {
        int stockQuantity = new BookStore(null).getStockQuantity(bookID);
        System.out.println(stockQuantity);
    }
}
