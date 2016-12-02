package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.Book;
import com.contribe.arbetsprov.BookID;
import com.contribe.arbetsprov.Inventory;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class FlatFileInventory implements Inventory {
    private final static File DATA_FILE = new File("bookstoredata.txt");
    Map<BookID, Integer> stock;
    Map<BookID, Book> books;

    FlatFileInventory() throws IOException, ParseException {
        stock = new HashMap<>();
        books = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] rowData = line.split(";");
            BookID bookID = new BookID(rowData[0]);
            String title = rowData[1];
            String author = rowData[2];
            DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
            numberFormat.setParseBigDecimal(true);
            BigDecimal price = (BigDecimal) numberFormat.parse(rowData[3]);
            int quantity = Integer.valueOf(rowData[4]);

            stock.put(bookID, quantity);
            books.put(bookID, new Book(bookID, title, author, price));
        }
    }

    @Override
    public int getStockQuantity(BookID bookID) {
        return stock.get(bookID);
    }

    @Override
    public void setStockQuantity(BookID bookID, int quantity) {
        stock.put(bookID, quantity);
        writeToFileIgnoringError();
    }

    private void writeToFileIgnoringError() {
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE));

        for (Map.Entry<BookID, Book> entry : books.entrySet()) {
            BookID bookID = entry.getKey();
            Book book = entry.getValue();
            int quantity = getStockQuantity(bookID);

            String[] data = {
                    bookID.isbn,
                    book.title,
                    book.author,
                    book.price.toPlainString(),
                    Integer.toString(quantity)
            };
            String line = String.join(";", data);
            writer.write(line);
//            writer.write(bookID.isbn);
//            writer.write(";");
//            writer.write(book.title);
//            writer.write(";");
//            writer.write(book.author);
//            writer.write(";");
//            writer.write(book.price.toPlainString());
//            writer.write(";");
//            writer.write(quantity);
            writer.write('\n');
        }
        writer.close();
    }

    @Override
    public boolean contains(BookID bookID) {
        return stock.containsKey(bookID);
    }

    @Override
    public void addBook(Book book, int quantity) {
        stock.put(book.id, quantity);
        books.put(book.id, book);
        writeToFileIgnoringError();
    }
}
