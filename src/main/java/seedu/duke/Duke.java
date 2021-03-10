package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandHandler;
import seedu.duke.command.ExitCommand;
import seedu.duke.parser.ParserHandler;
import seedu.duke.record.RecordList;
import seedu.duke.exception.CommandException;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private RecordList records;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        records = new RecordList();
        storage = new Storage();
    }

    /**
     * Runner for the FINUX Application.
     */
    private void run() {
        ui.printWelcomeMessage();
        commandLooper();
        //ui.printGoodByeMessage();
        System.out.println("PROGRAM TERMINATES HERE!");
    }

    private void commandLooper() {
        Command command;
        String rawInput;
        do {
            rawInput = ui.getUserInput();
            ArrayList<String> parsedStringList = ParserHandler.getParseInput(rawInput);
            command = parseCommand(parsedStringList);
            if (command != null) {
                command.execute(records, ui, storage);
            }
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    //Shift to ParserHandler class
    private Command parseCommand(ArrayList<String> parsedString) {
        try {
            Command type = CommandHandler.createCommand(parsedString);
            System.out.println("Command is parsed");
            return type;
        } catch (CommandException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
            return null;
        }
    }
}
