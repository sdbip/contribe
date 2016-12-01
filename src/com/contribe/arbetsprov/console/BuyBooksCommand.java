package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.BookID;
import com.contribe.arbetsprov.BookStore;
import com.contribe.arbetsprov.NoSuchBookException;
import com.contribe.arbetsprov.NotInStockException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class BuyBooksCommand implements Command {
    List<BookStore.LineItem> lineItems = new ArrayList<>();

    @Override
    public void parse(String[] args) throws ParseException {
        // Main buy-books {bookID quantity}*

        if (args.length < 4) throw new ParseException("", 0);
        if (args.length % 2 != 0) throw new ParseException("", 0);

        for (int i = 2; i < args.length; i+=2) {
            BookID bookID = new BookID(args[i]);
            int quantity = Integer.valueOf(args[i + 1]);
            lineItems.add(new BookStore.LineItem(bookID, quantity));
        }
    }

    @Override
    public void execute() {
        try {
            new BookStore(null).buy(lineItems);
        } catch (NoSuchBookException | NotInStockException e) {
            e.printStackTrace();
        }
    }
}
