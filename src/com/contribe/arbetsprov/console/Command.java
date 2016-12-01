package com.contribe.arbetsprov.console;

import com.contribe.arbetsprov.Inventory;

import java.text.ParseException;

interface Command {
    void parse(String[] args) throws ParseException;
    void execute(Inventory inventory);
}
