package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.ParserHandler;
import seedu.duke.record.RecordList;
import seedu.duke.record.Loan;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.common.Validators.validateDate;

public class ReturnCommandTest {

    @Test
    public void executeReturnCommand_returnCmd_success() {
        String testName = "executeReturnCommand_returnCmd_success";
        String expectedOutput = "=========================================================" + System.lineSeparator()
                + "Loan marked as returned: [L][2021-03-15] Loan to Andy [v]" + System.lineSeparator()
                + "=========================================================" + System.lineSeparator();
        String returnCmdInput = "return -i 1 -d 2021-03-16";
        runReturnCmdTest(expectedOutput, returnCmdInput, testName);
    }

    @Test
    public void executeReturnCommand_returnCmdTwo_success() {
        String testName = "executeReturnCommand_returnCmdTwo_success";
        String expectedOutput = "=========================================================" + System.lineSeparator()
                + "Loan marked as returned: [L][2021-03-16] Loan to Jason [v]" + System.lineSeparator()
                + "=========================================================" + System.lineSeparator();
        String returnCmdInput = "return -i 3 -d 2021-03-16";
        runReturnCmdTest(expectedOutput, returnCmdInput, testName);
    }

    private void runReturnCmdTest(String expectedOutput, String returnCmdInput, String testName) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        BorrowersCreditScoreForReturnedLoans borrowersCreditScoreForReturnedLoans =
                new BorrowersCreditScoreForReturnedLoans(new HashMap<>());
        RecordList loans = getLoanList();
        CommandHandler commandHandler = new CommandHandler();
        ByteArrayOutputStream returnCmdBos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(returnCmdBos));

        ParserHandler parserHandler = new ParserHandler();
        Command command = commandHandler.parseCommand(parserHandler.getParseInput(returnCmdInput), loans);

        assertTrue(command instanceof ReturnCommand,
                String.format("Failed test '%s' command object "
                        + "returned by parseCommand() is not an instance of ReturnCommand", testName));

        command.execute(loans, ui, storage, borrowersCreditScoreForReturnedLoans);
        System.setOut(System.out);
        assertTrue(returnCmdBos.toString().equals(expectedOutput), String.format("Failed test "
            + "'%s', wrong output.", testName));

    }

    private RecordList getLoanList() {
        RecordList loans = new RecordList();
        loans.addRecord(new Loan(new BigDecimal("43.28"), validateDate("2021/03/15"), "Loan to Andy", "Andy"));
        loans.addRecord(new Loan(new BigDecimal("89.23"), validateDate("2021/03/17"), "Loan to Mark", "Mark"));
        loans.addRecord(new Loan(new BigDecimal("5.67"), validateDate("2021/03/16"), "Loan to Jason", "Jason"));
        return loans;
    }
}
