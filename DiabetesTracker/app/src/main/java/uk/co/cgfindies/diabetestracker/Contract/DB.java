package uk.co.cgfindies.diabetestracker.Contract;

/**
 * Created by Scutterman on 04/04/2016.
 */

public interface DB extends org.droidparts.contract.DB {

    int VERSION = 1;
    String FILE = "diabetes_tracker.sqlite";

    public interface Table extends org.droidparts.contract.DB.Table {
        String READING = "reading";
    }

    public interface ReadingColumn extends org.droidparts.contract.DB.Column {
        String BLOOD_SUGAR_LEVEL = "blood_sugar_level";
        String DATE_LEVEL_TAKEN = "date_level_taken";
        String DATE_CREATED = "date_created";
    }

}