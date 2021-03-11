package seedu.duke.record;

public class Loan extends Record {
    private boolean isReturn;
    private String dueDate;
    private String returnDate;
    private String recordType;

    public Loan(double amount, String issuedDate, String description) {
        super(amount, issuedDate, description);
        this.isReturn = false;
    }

    public void markAsReturned() {
        this.isReturn = true;
    }

    public void setReturnDate(String date) {
        this.returnDate = date;
    }

    @Override
    public String toString() {
        //temporary placeholder. output format to be discussed.
        return "List loans!";
    }
}
