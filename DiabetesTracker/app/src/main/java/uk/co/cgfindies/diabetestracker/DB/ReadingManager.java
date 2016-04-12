package uk.co.cgfindies.diabetestracker.DB;

import android.content.Context;

import org.droidparts.persist.sql.EntityManager;

import java.util.Date;

import uk.co.cgfindies.diabetestracker.Model.Reading;

/**
 * Provides a way to manage Reading entities
 */
public class ReadingManager extends EntityManager<Reading> {

    /**
     * {@inheritDoc}
     */
    public ReadingManager(Context ctx)
    {
        super(Reading.class, ctx);
    }

    /**
     * Insert an entry in the Reading table, including an auto-set creation date
     * @param item The Reading entity to insert
     * @return whether the item was created successfully or not.
     */
    @Override
    public boolean create(Reading item) {
        item.date_created = new Date();
        return super.create(item);
    }
}
