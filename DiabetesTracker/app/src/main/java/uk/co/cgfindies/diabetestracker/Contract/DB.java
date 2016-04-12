package uk.co.cgfindies.diabetestracker.Contract;

/**
 * Provides data to be used in database management.
 */
public interface DB extends org.droidparts.contract.DB {

    /**
     * The database version
     */
    int VERSION = 1;

    /**
     * The database file
     */
    String FILE = "diabetes_tracker.sqlite";

    /**
     * The database tables
     */
    public interface Table extends org.droidparts.contract.DB.Table {
        String READING = "reading";
    }

    /**
     * The columns for the Reading table.
     */
    public interface ReadingColumn extends org.droidparts.contract.DB.Column {
        String BLOOD_SUGAR_LEVEL = "blood_sugar_level";
        String DATE_LEVEL_TAKEN = "date_level_taken";
        String DATE_CREATED = "date_created";
    }

}
