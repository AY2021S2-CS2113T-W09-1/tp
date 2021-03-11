package seedu.duke.command;

import seedu.duke.common.ArgumentType;
import seedu.duke.common.RecordType;
import seedu.duke.exception.CommandException;
import seedu.duke.record.RecordList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static seedu.duke.command.Utils.checkInvalidOptions;
import static seedu.duke.command.Utils.checkOptionConflict;
import static seedu.duke.command.Utils.hasOption;
import static seedu.duke.command.Utils.validateArguments;
import static seedu.duke.common.Constant.OPTION_EXPENSE;
import static seedu.duke.common.Constant.OPTION_LOAN;
import static seedu.duke.common.Constant.OPTION_SAVING;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final ArgumentType[] argumentTypeOrder = {
        ArgumentType.COMMAND,
        ArgumentType.OPTION,
        ArgumentType.EMPTY_VALUE
    };
    protected static final String COMMAND_LIST = "list";

    private RecordType recordType;

    public ListCommand(ArrayList<String> arguments) throws CommandException {
        checkInvalidOptions(arguments, COMMAND_LIST, OPTION_EXPENSE, OPTION_LOAN, OPTION_SAVING);
        checkOptionConflict(arguments, COMMAND_LIST, OPTION_EXPENSE, OPTION_LOAN, OPTION_SAVING);

        if (hasOption(arguments, OPTION_EXPENSE)) {
            recordType = RecordType.EXPENSE;
        } else if (hasOption(arguments, OPTION_LOAN)) {
            recordType = RecordType.LOAN;
        } else if (hasOption(arguments, OPTION_SAVING)) {
            recordType = RecordType.SAVING;
        } else {
            throw new CommandException("missing option: [-e | -l | -s]", COMMAND_LIST);
        }
        validateArguments(arguments, argumentTypeOrder, COMMAND_LIST);
    }

    @Override
    public void execute(RecordList recordList, Ui ui, Storage storage) {
        switch (recordType) {
        case EXPENSE:
            ui.printExpenses();
            break;
        case LOAN:
            ui.printLoans();
            break;
        case SAVING:
            // Fallthrough
        default:
            ui.printSavings();
        }
    }
}
