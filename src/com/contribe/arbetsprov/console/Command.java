package com.contribe.arbetsprov.console;

import java.text.ParseException;

interface Command {
    void parse(String[] args) throws ParseException;
    void execute();
}
