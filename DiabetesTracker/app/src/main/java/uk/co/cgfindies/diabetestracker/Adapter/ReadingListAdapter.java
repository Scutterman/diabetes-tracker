package uk.co.cgfindies.diabetestracker.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import org.droidparts.adapter.cursor.EntityCursorAdapter;
import org.droidparts.adapter.holder.Text2Holder;
import org.droidparts.persist.sql.stmt.Select;

import uk.co.cgfindies.diabetestracker.Model.Reading;
import uk.co.cgfindies.diabetestracker.R;

/**
 * Provides a ListAdapter for Reading entities
 */
public class ReadingListAdapter extends EntityCursorAdapter<Reading> {
    public ReadingListAdapter(Context ctx, Select<Reading> select) {
        super(ctx, Reading.class, select);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = getLayoutInflater().inflate(R.layout.list_row_reading, null);
        Text2Holder holder = new Text2Holder(view);
        view.setTag(holder);

        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindView(Context context, View view, Reading reading) {
        Text2Holder holder = (Text2Holder) view.getTag();
        holder.text1.setText(Double.toString(reading.bloodSugarLevel));

        holder.text2.setText(Reading.getRelativeDate(getContext(), reading.date_level_taken.getTime()));
    }
}
