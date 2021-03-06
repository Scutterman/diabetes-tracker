package uk.co.cgfindies.diabetestracker.Model;

import android.content.Context;
import android.text.format.DateUtils;

import org.droidparts.annotation.sql.Column;
import org.droidparts.annotation.sql.Table;
import org.droidparts.model.Entity;

import java.util.Date;

import uk.co.cgfindies.diabetestracker.Contract.DB;

/**
 * Provides a container for a row in the Reading table.
 */
@Table(name = DB.Table.READING)
public class Reading extends Entity
{
    @Column(name = DB.ReadingColumn.BLOOD_SUGAR_LEVEL)
    public double bloodSugarLevel;

    @Column(name = DB.ReadingColumn.DATE_LEVEL_TAKEN)
    public Date date_level_taken;

    @Column(name = DB.ReadingColumn.DATE_CREATED)
    public Date date_created;

    /**
     * Get a relative date string from a timestamp
     * @param ctx A context object
     * @param time The timestamp to get a relative time for
     * @return The relative date string.
     */
    public static CharSequence getRelativeDate(Context ctx, long time)
    {
        return DateUtils.getRelativeDateTimeString(
            ctx,
            time,
            DateUtils.MINUTE_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS,
            0
        );
    }

    /**
     * @return A Clone of this object
     */
    public Reading clone()
    {
        Reading clone = new Reading();
        clone.bloodSugarLevel = bloodSugarLevel;
        clone.date_level_taken = date_level_taken;
        clone.date_created = date_created;
        clone.id = id;
        return clone;
    }

}
