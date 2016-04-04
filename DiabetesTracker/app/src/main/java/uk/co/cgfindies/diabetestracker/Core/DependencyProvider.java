package uk.co.cgfindies.diabetestracker.Core;

import android.content.Context;

import org.droidparts.AbstractDependencyProvider;
import org.droidparts.persist.sql.AbstractDBOpenHelper;

/**
 * Created by Scutterman on 04/04/2016.
 */
public class DependencyProvider extends AbstractDependencyProvider {
    private final DBOpenHelper dbOpenHelper;

    public DependencyProvider(Context ctx) {
        super(ctx);
        dbOpenHelper = new DBOpenHelper(ctx);
    }

    @Override
    public AbstractDBOpenHelper getDBOpenHelper() {
        return dbOpenHelper;
    }

}
