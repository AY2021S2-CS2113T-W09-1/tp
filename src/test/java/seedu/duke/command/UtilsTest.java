package seedu.duke.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.common.ArgumentType;
import seedu.duke.exception.CommandException;
import seedu.duke.parser.ParserHandler;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.command.HelpCommand.COMMAND_HELP;
import static seedu.duke.command.Utils.isOption;
import static seedu.duke.command.Utils.validateArguments;
import static seedu.duke.command.ViewCommand.COMMAND_VIEW;

class UtilsTest {
    private static final ArgumentType[] ARG_TYPE_ORDER_CMD_HELP = { ArgumentType.COMMAND };
    private static final ArgumentType[] ARG_TYPE_ORDER_CMD_VIEW = {
        ArgumentType.COMMAND,
        ArgumentType.OPTION,
        ArgumentType.EMPTY_VALUE
    };

    @DisplayName("[isOption] - Valid Options - success:")
    @Test
    public void isOption_optionMatch_success() {
        assertAll(
            () -> assertTrue(isOption("-e")),
            () -> assertTrue(isOption("-l")),
            () -> assertTrue(isOption("-s")),
            () -> assertTrue(isOption("-d")),
            () -> assertTrue(isOption("-a")),
            () -> assertTrue(isOption("-i")),
            () -> assertTrue(isOption("-E")),
            () -> assertTrue(isOption("-L")),
            () -> assertTrue(isOption("-S")),
            () -> assertTrue(isOption("-D")),
            () -> assertTrue(isOption("-A")),
            () -> assertTrue(isOption("-I")),
            () -> assertTrue(isOption("-p"))
        );
    }

    @DisplayName("[isOption] - Invalid Options - failure:")
    @Test
    public void isOption_invalidOptions() {
        assertAll(
            () -> assertFalse(isOption("--")),
            () -> assertFalse(isOption("-[")),
            () -> assertFalse(isOption("- ")),
            () -> assertFalse(isOption("-1")),
            () -> assertFalse(isOption("-9")),
            () -> assertFalse(isOption("-hello"))
        );
    }

    private void validateArguments_improperCommand_helper(ArrayList<String> arguments,
                                                          ArgumentType[] argumentTypeOrder,
                                                          String expected,
                                                          String command) {
        CommandException e = assertThrows(
            CommandException.class,
            () -> validateArguments(arguments, argumentTypeOrder, command)
        );
        if (!e.getMessage().equals(expected)) {
            fail(arguments.toString() + " - Error: " + e.getMessage());
        }
    }

    @DisplayName("[validateArguments] - help Command - success:")
    @Test
    public void validateArguments_properHelp_success() {
        ArrayList<String> command = ParserHandler.getParseInput("help");
        try {
            validateArguments(command, ARG_TYPE_ORDER_CMD_HELP, COMMAND_HELP);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @DisplayName("[validateArguments] - help Command - failure:")
    @Test
    public void validateArguments_improperHelp() {
        String expected1245 = "invalid command order, expected command word.";
        String expected3 = COMMAND_HELP + " Command - too many arguments.";

        ArrayList<String> command1 = ParserHandler.getParseInput("help gerard oi");
        validateArguments_improperCommand_helper(command1, ARG_TYPE_ORDER_CMD_HELP,
                expected1245, COMMAND_HELP);

        ArrayList<String> command2 = ParserHandler.getParseInput("help -z oi");
        validateArguments_improperCommand_helper(command2, ARG_TYPE_ORDER_CMD_HELP,
                expected1245, COMMAND_HELP);

        ArrayList<String> command3 = ParserHandler.getParseInput("help -a");
        validateArguments_improperCommand_helper(command3, ARG_TYPE_ORDER_CMD_HELP,
                expected3, COMMAND_HELP);

        ArrayList<String> command4 = ParserHandler.getParseInput("help me");
        validateArguments_improperCommand_helper(command4, ARG_TYPE_ORDER_CMD_HELP,
                expected1245, COMMAND_HELP);

        ArrayList<String> command5 = ParserHandler.getParseInput("helpz");
        validateArguments_improperCommand_helper(command5, ARG_TYPE_ORDER_CMD_HELP,
                expected1245, COMMAND_HELP);
    }

    @DisplayName("[validateArguments] - view Command - success:")
    @Test
    public void validateArguments_properView_success() {
        // By assumption that options are valid and order is correct.
        ArrayList<String> command1 = ParserHandler.getParseInput("view -e");
        ArrayList<String> command2 = ParserHandler.getParseInput("view -l");
        ArrayList<String> command3 = ParserHandler.getParseInput("view -s");
        try {
            validateArguments(command1, ARG_TYPE_ORDER_CMD_VIEW, COMMAND_VIEW);
            validateArguments(command2, ARG_TYPE_ORDER_CMD_VIEW, COMMAND_VIEW);
            validateArguments(command3, ARG_TYPE_ORDER_CMD_VIEW, COMMAND_VIEW);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @DisplayName("[validateArguments] - view Command - failure:")
    @Test
    public void validateArguments_improperView() {
        String expected1 = "view Command - invalid input: ";
        String expected25 = "view Command - not enough arguments.";
        String expected34 = "view Command - too many arguments.";

        ArrayList<String> command1 = ParserHandler.getParseInput("view -l abc");
        validateArguments_improperCommand_helper(command1, ARG_TYPE_ORDER_CMD_VIEW,
                expected1 + "abc", COMMAND_VIEW);

        ArrayList<String> command2 = ParserHandler.getParseInput("view -z -z");
        validateArguments_improperCommand_helper(command2, ARG_TYPE_ORDER_CMD_VIEW,
                expected25, COMMAND_VIEW);

        ArrayList<String> command3 = ParserHandler.getParseInput("view -s -s");
        validateArguments_improperCommand_helper(command3, ARG_TYPE_ORDER_CMD_VIEW,
                expected34, COMMAND_VIEW);

        ArrayList<String> command4 = ParserHandler.getParseInput("view -l -l");
        validateArguments_improperCommand_helper(command4, ARG_TYPE_ORDER_CMD_VIEW,
                expected34, COMMAND_VIEW);

        ArrayList<String> command5 = ParserHandler.getParseInput("view");
        validateArguments_improperCommand_helper(command5, ARG_TYPE_ORDER_CMD_VIEW,
                expected25, COMMAND_VIEW);
    }
}
