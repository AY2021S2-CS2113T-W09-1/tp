package seedu.duke.command;

import seedu.duke.common.ArgumentType;
import seedu.duke.exception.CommandException;
import seedu.duke.record.RecordList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static seedu.duke.command.Utils.checkInvalidOptions;
import static seedu.duke.command.Utils.checkOptionConflict;
import static seedu.duke.command.Utils.getOptionValue;
import static seedu.duke.command.Utils.hasOption;
import static seedu.duke.command.Utils.validateArguments;
import static seedu.duke.common.Constant.OPTION_INDEX;

public class RemoveCommand extends Command {
    private static final ArgumentType[] argumentTypeOrder = {
        ArgumentType.COMMAND,
        ArgumentType.OPTION,
        ArgumentType.VALUE
    };
    protected static final String COMMAND_REMOVE = "remove";

    private String recordNumber;

    public RemoveCommand(ArrayList<String> arguments) throws CommandException {
        checkInvalidOptions(arguments, COMMAND_REMOVE, OPTION_INDEX);
        checkOptionConflict(arguments, COMMAND_REMOVE, OPTION_INDEX);
        validateArguments(arguments, argumentTypeOrder, COMMAND_REMOVE);

        if (hasOption(arguments, OPTION_INDEX)) {
            recordNumber = getOptionValue(arguments, COMMAND_REMOVE, OPTION_INDEX);
        } else {
            throw new CommandException("missing option: -i", COMMAND_REMOVE);
        }
    }

    /**
     * Executes the delete function.
     * Prints a message containing the record that will be removed.
     * Removes the record at the specified index.
     *
     * @param records is the recordList.
     * @param ui is the Ui object that interacts with the user.
     * @param storage is the Storage object that reads and writes to the save file.
     */
    @Override
    public void execute(RecordList records, Ui ui, Storage storage) {
        int recordNumberInt = Integer.parseInt(recordNumber);
        int recordNumberInList = recordNumberInt - 1;
        // Object description is used here, may need to be replaced with the full record entry once
        // list structure is finalized
        String recordName = records.getRecordAt(recordNumberInList).getDescription();
        ui.printMessage("Record to remove: " + recordName);
        records.deleteRecordAt(recordNumberInList);
    }
}
