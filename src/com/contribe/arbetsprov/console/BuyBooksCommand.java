package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class BuyBooksCommand implements Command {
    List<BookStore.LineItem> lineItems = new ArrayList<>();

    @Override
    public void parse(String[] args) throws ParseException {
        // Main buy-books {bookID quantity}*

        if (args.length < 3) throw new ParseException("", 0);
        if (args.length % 2 != 1) throw new ParseException("", 0);

        for (int i = 1; i < args.length; i+=2) {
            BookID bookID = new BookID(args[i]);
            int quantity = Integer.valueOf(args[i + 1]);
            lineItems.add(new BookStore.LineItem(bookID, quantity));
        }
    }

    @Override
    public void execute(Inventory inventory) {
        try {
            new BookStore(inventory).buy(lineItems);
        } catch (NoSuchBookException | NotInStockException e) {
            e.printStackTrace();
        }

        for (BookStore.LineItem lineItem : lineItems) {
            System.out.println("Bought " + lineItem.quantity + " of " + lineItem.bookID);
            System.out.println("There are now " + inventory.getStockQuantity(lineItem.bookID) + " items left.");
        }
    }
}
