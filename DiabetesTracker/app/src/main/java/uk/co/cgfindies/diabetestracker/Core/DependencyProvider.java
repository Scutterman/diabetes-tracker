package uk.co.cgfindies.diabetestracker.Core;

import android.content.Context;

import org.droidparts.AbstractDependencyProvider;
import org.droidparts.persist.sql.AbstractDBOpenHelper;

import uk.co.cgfindies.diabetestracker.DB.ReadingManager;

/**
 * Provide dependencies to the app.
 */
public class DependencyProvider extends AbstractDependencyProvider {
    private final DBOpenHelper dbOpenHelper;
    private ReadingManager readingManager;

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

    /**
     * Inject a ReadingManager
     * @param ctx The context used to create the ReadingManager
     * @return a ReadingManager instance.
     */
    public ReadingManager getReadingManager(Context ctx)
    {
        if (readingManager == null)
        {
            readingManager = new ReadingManager(ctx);
        }

        return readingManager;
    }

}
