package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.Book;
import com.contribe.arbetsprov.BookID;
import com.contribe.arbetsprov.Inventory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class AddBookCommand implements Command {
    private Book book;
    private int quantity;

    @Override
    public void parse(String[] args) throws ParseException {
        // Main add-book bookID title author price

        if (args.length != 6) throw new ParseException("", 0);
        BookID bookID = new BookID(args[1]);
        String title = args[2];
        String author = args[3];
        BigDecimal price = getBigDecimal(args[4]);

        book = new Book(bookID, title, author, price);
        quantity = Integer.valueOf(args[5]);
    }

    private static BigDecimal getBigDecimal(String arg) throws ParseException {
        DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setParseBigDecimal(true);
        return (BigDecimal) numberFormat.parse(arg);
    }

    @Override
    public void execute(Inventory inventory) {
        inventory.addBook(book, quantity);
    }
}
