package uk.co.cgfindies.diabetestracker.DB;

import android.content.Context;

import org.droidparts.persist.sql.EntityManager;

import java.util.Date;

import uk.co.cgfindies.diabetestracker.Model.Reading;

/**
 * Created by Scutterman on 08/04/2016.
 */
public class ReadingManager extends EntityManager<Reading> {

    public ReadingManager(Context ctx)
    {
        super(Reading.class, ctx);
    }

    @Override
    public boolean create(Reading item) {
        item.date_created = new Date();
        return super.create(item);
    }
}
