package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.Book;
import com.contribe.arbetsprov.BookStore;
import com.contribe.arbetsprov.Inventory;

import java.text.ParseException;
import java.util.Set;

class FindBooksCommand implements Command {

    private String searchString;

    @Override
    public void parse(String[] args) throws ParseException {
        // Main find-books "searchString"

        if (args.length != 2) throw new ParseException("", 0);
        searchString = args[1];
        System.out.println("Findings books matching: " + searchString);
    }

    @Override
    public void execute(Inventory inventory) {
        Set<Book> books = new BookStore(inventory).list(searchString);
        for (Book book : books) {
            System.out.println(book.id.isbn);
            System.out.print(book.title);
            System.out.print(' ');
            System.out.print(book.author);
            System.out.print(' ');
            System.out.println(book.price.toPlainString());
        }
    }
}
