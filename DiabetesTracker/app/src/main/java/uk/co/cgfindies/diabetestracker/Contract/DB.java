package uk.co.cgfindies.diabetestracker.Contract;

/**
 * Created by Scutterman on 04/04/2016.
 */

public interface DB extends org.droidparts.contract.DB {

    int VERSION = 1;
    String FILE = "diabetes_tracker.sqlite";

    public interface Table extends org.droidparts.contract.DB.Table {

    }

    public interface Column extends org.droidparts.contract.DB.Column {

    }

}