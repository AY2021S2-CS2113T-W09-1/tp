package seedu.duke.ui;

import seedu.duke.record.RecordList;
import seedu.duke.record.Record;
import seedu.duke.record.Expense;
import seedu.duke.record.Saving;
import seedu.duke.record.Loan;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private static final String logo = "=========================================================\n"
            + "||    $$$$$$  $$$$$$  $$    $$  $$    $$   $$    $$    ||\n"
            + "||    $$        $$    $$$   $$  $$    $$    $$  $$     ||\n"
            + "||    $$$$$$    $$    $$ $$ $$  $$    $$      $$       ||\n"
            + "||    $$        $$    $$   $$$  $$    $$    $$  $$     ||\n"
            + "||    $$      $$$$$$  $$    $$   $$$$$$    $$    $$    ||\n"
            + "=========================================================\n";

    private static final String DIVIDER = "=========================================================";
    private static final String MESSAGE_GREETING = "";
    private static final String MESSAGE_GOODBYE = "HUAT AH!";
    private static final String MESSAGE_LOADING = "Loading from save... ";
    private static final String MESSAGE_SUCCESSFULLY_ADDED = "Record has been added...";
    private static final String MESSAGE_TOTAL_EXPENSE = "The total amount for expense is ";
    private static final String MESSAGE_TOTAL_LOAN = "The total amount for loan is ";
    private static final String MESSAGE_TOTAL_SAVING = "The total amount for saving is ";


    /**
     * Decorative prefix for the FINUX Interface.
     */
    private static final String FINUX_PREFIX = "$$";

    private final Scanner input;
    private RecordList recordList;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.input = new Scanner(in);
    }

    public void setRecordList(RecordList recordList) {
        this.recordList = recordList;
    }

    public static void printSuccessfulAdd(Record recordToAdd) {
        System.out.println(DIVIDER);
        System.out.println();
        System.out.println(MESSAGE_SUCCESSFULLY_ADDED);
        System.out.println("\n" + recordToAdd.getDescription() + " " + recordToAdd.getAmount()
                + " " + recordToAdd.getIssueDate());
        System.out.println();
        System.out.println(DIVIDER);
    }

    public String getUserInput() {
        System.out.print(FINUX_PREFIX + " ");
        String userInput = input.nextLine().strip();
        return userInput;
    }

    public void printWelcomeMessage() {
        System.out.println(logo);
        System.out.println(MESSAGE_LOADING + fileLoadStatus());
    }

    public void printGoodByeMessage() {
        System.out.println(DIVIDER);
        System.out.println();
        System.out.println(MESSAGE_GOODBYE);
        System.out.println();
        System.out.println(DIVIDER);
    }

    public void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private String fileLoadStatus() {
        return "Successful";
    }

    public void printExpenses() {
        System.out.println(DIVIDER);
        for (int i = 0; i < recordList.getRecordCount(); i++) {
            Record currentRecord = recordList.getRecordAt(i);
            if (currentRecord instanceof Expense) {
                System.out.println(currentRecord);
            }
        }
        System.out.println(DIVIDER);
    }

    public void printLoans() {
        System.out.println(DIVIDER);
        for (int i = 0; i < recordList.getRecordCount(); i++) {
            Record currentRecord = recordList.getRecordAt(i);
            if (currentRecord instanceof Loan) {
                System.out.println(currentRecord);
            }
        }
        System.out.println(DIVIDER);
    }

    public void printSavings() {
        System.out.println(DIVIDER);
        for (int i = 0; i < recordList.getRecordCount(); i++) {
            Record currentRecord = recordList.getRecordAt(i);
            if (currentRecord instanceof Saving) {
                System.out.println(currentRecord);
            }
        }
        System.out.println(DIVIDER);
    }

    public void viewTotalAmountExpense() {
        System.out.println(DIVIDER);
        double totalAmount = 0;
        for (int i = 0; i < recordList.getRecordCount(); i++) {
            Record currentRecord = recordList.getRecordAt(i);
            if (currentRecord instanceof Expense) {
                totalAmount = totalAmount + currentRecord.getAmount();
            }
        }
        System.out.println(MESSAGE_TOTAL_EXPENSE + totalAmount);
        System.out.println(DIVIDER);
    }

    public void viewTotalAmountLoan() {
        System.out.println(DIVIDER);
        double totalAmount = 0;
        for (int i = 0; i < recordList.getRecordCount(); i++) {
            Record currentRecord = recordList.getRecordAt(i);
            if (currentRecord instanceof Loan) {
                totalAmount = totalAmount + currentRecord.getAmount();
            }
        }
        System.out.println(MESSAGE_TOTAL_LOAN + totalAmount);
        System.out.println(DIVIDER);
    }

    public void viewTotalAmountSaving() {
        System.out.println(DIVIDER);
        double totalAmount = 0;
        for (int i = 0; i < recordList.getRecordCount(); i++) {
            Record currentRecord = recordList.getRecordAt(i);
            if (currentRecord instanceof Saving) {
                totalAmount = totalAmount + currentRecord.getAmount();
            }
        }
        System.out.println(MESSAGE_TOTAL_SAVING + totalAmount);
        System.out.println(DIVIDER);
    }
}
