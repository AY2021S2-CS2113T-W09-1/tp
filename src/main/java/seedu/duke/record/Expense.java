package seedu.duke.record;

public class Expense extends Record {
    private static final String TYPE_EXPENSE = "E";
    private static final String FILE_OUTPUT_STRING_FORMAT = "| %s | %s | %f | %s";

    public Expense(double amount, String issuedDate, String description) {
        super(amount, issuedDate, description);
    }

    @Override
    public String convertFileFormat() {
        return String.format(FILE_OUTPUT_STRING_FORMAT, TYPE_EXPENSE, super.getDescription(),
                super.getAmount(), super.getIssueDate());
    }

    @Override
    public String toString() {
        //temporary placeholder. output format to be discussed.
        return "List expenses!";
    }
}
