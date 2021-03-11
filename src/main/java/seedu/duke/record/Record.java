package seedu.duke.record;

public abstract class Record {
    private double amount;
    private String issueDate;
    private String description;
    private static final String FILE_OUTPUT_STRING_FORMAT = "| %s | %f | %s";

    public Record(double amount, String issueDate, String description) {
        this.amount = amount;
        this.issueDate = issueDate;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getIssueDate() {
        return this.issueDate;
    }

    public String convertFileFormat() {
        return String.format(FILE_OUTPUT_STRING_FORMAT, description, amount, issueDate);
    }

    @Override
    public String toString() {
        //temporary placeholder. output format to be discussed.
        return "Common description across Loan, Saving & Expense classes";
    }
}
