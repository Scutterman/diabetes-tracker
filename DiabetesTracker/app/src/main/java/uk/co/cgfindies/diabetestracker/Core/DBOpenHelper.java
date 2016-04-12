package uk.co.cgfindies.diabetestracker.Core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import uk.co.cgfindies.diabetestracker.Contract.DB;
import uk.co.cgfindies.diabetestracker.Model.Reading;

/**
 * Provides a database
 */
public class DBOpenHelper extends org.droidparts.persist.sql.AbstractDBOpenHelper
{
    public DBOpenHelper(Context ctx)
    {
        super(ctx, DB.FILE, DB.VERSION);
    }

    /**
     * Initial database setup
     * @param db
     */
    @Override
    protected void onCreateTables(SQLiteDatabase db)
    {
        createTables(db, Reading.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
