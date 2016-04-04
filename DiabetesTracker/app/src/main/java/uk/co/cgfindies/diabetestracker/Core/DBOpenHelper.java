package uk.co.cgfindies.diabetestracker.Core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import uk.co.cgfindies.diabetestracker.Contract.DB;

/**
 * Created by Scutterman on 04/04/2016.
 */
public class DBOpenHelper extends org.droidparts.persist.sql.AbstractDBOpenHelper {
    public DBOpenHelper(Context ctx) {
        super(ctx, DB.FILE, DB.VERSION);
    }

    @Override
    protected void onCreateTables(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
