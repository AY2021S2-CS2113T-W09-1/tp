package seedu.duke.command;

import seedu.duke.exception.CommandException;
import seedu.duke.record.RecordList;
import seedu.duke.ui.Ui;

import static seedu.duke.command.ListCommand.COMMAND_LIST;
import static seedu.duke.command.AddCommand.COMMAND_ADD;
import static seedu.duke.command.ReturnCommand.COMMAND_RETURN;
import static seedu.duke.command.RemoveCommand.COMMAND_REMOVE;
import static seedu.duke.command.ViewCommand.COMMAND_VIEW;
import static seedu.duke.command.HelpCommand.COMMAND_HELP;
import static seedu.duke.command.ExitCommand.COMMAND_EXIT;
import static seedu.duke.command.CreditScoreCommand.COMMAND_CREDIT_SCORE;
import static seedu.duke.common.Constant.FINUX_LOGGER;

import java.util.ArrayList;

/**
 * Maps the command word to the subsequent {@code Command} object.
 * Creates the {@code Command} object with the arguments.
 */
public class CommandHandler {
    private static final String ERROR_INVALID_COMMAND = "Invalid command: ";
    private static final String LOGGER_OKAY_MESSAGE = "command object successfully created.";
    private static final int INDEX_OF_COMMAND = 0;
    private boolean isExit;

    private Command createCommand(ArrayList<String> parsedArguments, RecordList recordList)
            throws CommandException {
        String commandWord = parsedArguments.get(INDEX_OF_COMMAND);

        switch (commandWord) {
        case COMMAND_LIST:
            return new ListCommand(parsedArguments);
        case COMMAND_ADD:
            return new AddCommand(parsedArguments);
        case COMMAND_RETURN:
            return new ReturnCommand(parsedArguments, recordList);
        case COMMAND_REMOVE:
            return new RemoveCommand(parsedArguments, recordList);
        case COMMAND_VIEW:
            return new ViewCommand(parsedArguments);
        case COMMAND_HELP:
            return new HelpCommand(parsedArguments);
        case COMMAND_EXIT:
            setExit(true);
            return new ExitCommand(parsedArguments);
        case COMMAND_CREDIT_SCORE:
            return new CreditScoreCommand(parsedArguments);
        case "":
            return null;
        default:
            throw new CommandException(ERROR_INVALID_COMMAND + commandWord);
        }
    }

    /**
     * Parses the {@code parsedArguments} into a subsequent {@code Command}.
     *
     * @param parsedArguments the {@code ArrayList<String>} of arguments.
     * @param recordList the {@code RecordList} required for some {@code Command} objects.
     * @return the parsed {@code Command}.
     */
    public Command parseCommand(ArrayList<String> parsedArguments, RecordList recordList) {
        try {
            Command command = createCommand(parsedArguments, recordList);
            FINUX_LOGGER.logInfo(LOGGER_OKAY_MESSAGE);
            return command;
        } catch (CommandException e) {
            Ui.printError(e.getMessage());
            FINUX_LOGGER.logWarning(e.getMessage());
            return null;
        }
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
