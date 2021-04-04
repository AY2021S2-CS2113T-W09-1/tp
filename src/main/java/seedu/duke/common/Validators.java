package seedu.duke.common;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

import seedu.duke.exception.CustomException;
import seedu.duke.record.RecordList;
import static seedu.duke.common.Constant.FINUX_LOGGER;

public class Validators {
    private static final String KEYWORD_TODAY = "today";
    private static final String LOGGER_OKAY_MESSAGE = "index validation success";
    private static final long ERA_AD = 1;

    private static final DateTimeFormatter[] POSSIBLE_DATE_FORMATS = {
        new DateTimeFormatterBuilder().appendPattern("ddMMyyyy")
                                      .parseDefaulting(ChronoField.ERA, ERA_AD)
                                      .toFormatter()
                                      .withResolverStyle(ResolverStyle.STRICT),
        new DateTimeFormatterBuilder().appendPattern("d.M.yyyy")
                                      .parseDefaulting(ChronoField.ERA, ERA_AD)
                                      .toFormatter()
                                      .withResolverStyle(ResolverStyle.STRICT),
        new DateTimeFormatterBuilder().appendPattern("d-M-yyyy")
                                      .parseDefaulting(ChronoField.ERA, ERA_AD)
                                      .toFormatter()
                                      .withResolverStyle(ResolverStyle.STRICT),
        new DateTimeFormatterBuilder().appendPattern("d/M/yyyy")
                                      .parseDefaulting(ChronoField.ERA, ERA_AD)
                                      .toFormatter()
                                      .withResolverStyle(ResolverStyle.STRICT),
        new DateTimeFormatterBuilder().appendPattern("yyyy.M.d")
                                      .parseDefaulting(ChronoField.ERA, ERA_AD)
                                      .toFormatter()
                                      .withResolverStyle(ResolverStyle.STRICT),
        new DateTimeFormatterBuilder().appendPattern("yyyy-M-d")
                                      .parseDefaulting(ChronoField.ERA, ERA_AD)
                                      .toFormatter()
                                      .withResolverStyle(ResolverStyle.STRICT),
        new DateTimeFormatterBuilder().appendPattern("yyyy/M/d")
                                      .parseDefaulting(ChronoField.ERA, ERA_AD)
                                      .toFormatter()
                                      .withResolverStyle(ResolverStyle.STRICT)
    };

    // @@author marklowsk-reused
    // Reused from https://github.com/marklowsk/ip/blob/master/src/main/java/duke/common/Utils.java
    private static LocalDate parseDateString(DateTimeFormatter format, String dateInput) {
        try {
            return LocalDate.parse(dateInput, format);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // @@author marklowsk-reused
    // Reused from https://github.com/marklowsk/ip/blob/master/src/main/java/duke/common/Utils.java
    //   Utils.parseDate(String) with new additions.
    /**
     * Parses dateInput into a LocalDateTime object.
     * Returns null if dateInput cannot be parsed into a LocalDateTime object.
     *
     * @param dateInput a string containing the date supplied from the user.
     * @return a LocalDateTime object or null if failed to parse dateInput.
     * @see #parseDateString(DateTimeFormatter, String)
     */
    public static LocalDate validateDate(String dateInput) throws DateTimeException {
        assert dateInput != null : "dateInput is null!";
        if (dateInput.trim().equals(KEYWORD_TODAY)) {
            return LocalDate.now();
        }
        for (DateTimeFormatter dtf : POSSIBLE_DATE_FORMATS) {
            LocalDate date = parseDateString(dtf, dateInput);
            if (date != null) {
                return date;
            }
        }
        throw new DateTimeException("input \"" + dateInput + "\" is an invalid date.");
    }

    /**
     * Validates the index supplied by the user.
     * Throws exception if the index is out of range or is negative.
     *
     * @param inputToCheck index supplied by user to be validated.
     * @param records list of all records.
     * @return validated index.
     * @throws NumberFormatException when the inputToCheck contains non numeric.
     * @throws IndexOutOfBoundsException when the inputToCheck is less than zero, equals to zero or greater than the
     *     size of the records.
     */
    public static int validateIndex(String inputToCheck, RecordList records) throws NumberFormatException,
            IndexOutOfBoundsException {
        int recordNumberInList = Integer.parseInt(inputToCheck) - 1;
        records.getRecordAt(recordNumberInList);
        assert recordNumberInList > -1 : "recordNumberInList should be greater than 0 and non-negative";
        FINUX_LOGGER.logInfo(LOGGER_OKAY_MESSAGE);
        return recordNumberInList;
    }

    /**
     * Parse the stringAmount into a BigDecimal object.
     * @param stringAmount contains a string representing the amount entered.
     * @return a BigDecimal object.
     * @throws NumberFormatException when the stringAmount contains non numeric.
     * @throws CustomException when the numeric amount is less than or equals to zero.
     */
    public static BigDecimal validateAmount(String stringAmount) throws NumberFormatException,
            CustomException {
        BigDecimal amount = new BigDecimal(stringAmount);
        if (!(amount.compareTo(new BigDecimal("0")) == 1)) {
            throw new CustomException("amount must be greater than 0.");
        }
        return amount;
    }

}
