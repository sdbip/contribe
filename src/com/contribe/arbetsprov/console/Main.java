package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.Inventory;

import java.text.ParseException;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        try {
            Command command = parseArgs(args);
            Inventory inventory = new InMemoryInventory();
            command.execute(inventory);
        } catch (ParseException e) {
            printUsage();
            exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: ");
        System.out.println("\tMain buy-books {bookID quantity}*");
        System.out.println("\tMain lookup-quantity bookID");
    }

    private static Command parseArgs(String[] args) throws ParseException {
        if (args.length < 2) throw new ParseException("", 0);
        String commandName = args[1];
        Command command = byName(commandName);
        command.parse(args);
        return command;
    }

    private static Command byName(String commandName) throws ParseException {
        switch (commandName) {
            case "buy-books": return new BuyBooksCommand();
            case "lookup-quantity": return new LookupQuantityCommand();
            default: throw new ParseException("commandName", 0);
        }
    }
}
