package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.Inventory;

import java.io.IOException;
import java.text.ParseException;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        try {
            Command command = parseArgs(args);
            Inventory inventory = new FlatFileInventory();
            command.execute(inventory);
        } catch (ParseException e) {
            printUsage();
            exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printUsage() {
        System.out.println("Usage: ");
        System.out.println("\tMain add-book bookID title author price quantity");
        System.out.println("\tMain buy-books {bookID quantity}*");
        System.out.println("\tMain lookup-quantity bookID");
        System.out.println("\tMain find-books \"searchString\"");
    }

    private static Command parseArgs(String[] args) throws ParseException {
        if (args.length < 1) throw new ParseException("", 0);
        String commandName = args[0];
        Command command = byName(commandName);
        command.parse(args);
        return command;
    }

    private static Command byName(String commandName) throws ParseException {
        switch (commandName) {
            case "add-book": return new AddBookCommand();
            case "buy-books": return new BuyBooksCommand();
            case "lookup-quantity": return new LookupQuantityCommand();
            case "find-books": return new FindBooksCommand();
            default: throw new ParseException("commandName", 0);
        }
    }
}
