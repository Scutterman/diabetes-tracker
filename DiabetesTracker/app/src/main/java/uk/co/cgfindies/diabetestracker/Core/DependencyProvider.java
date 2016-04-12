package uk.co.cgfindies.diabetestracker.Core;

import android.content.Context;

import org.droidparts.AbstractDependencyProvider;
import org.droidparts.persist.sql.AbstractDBOpenHelper;

/**
 * Provide dependencies to the app.
 */
public class DependencyProvider extends AbstractDependencyProvider {
    private final DBOpenHelper dbOpenHelper;

    /**
     * Set up the dependencies, including the  DBOpenHelper
     */
    public DependencyProvider(Context ctx) {
        super(ctx);
        dbOpenHelper = new DBOpenHelper(ctx);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractDBOpenHelper getDBOpenHelper() {
        return dbOpenHelper;
    }

}
