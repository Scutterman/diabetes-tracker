package uk.co.cgfindies.diabetestracker.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.droidparts.annotation.inject.InjectDependency;
import org.droidparts.fragment.support.v4.ListFragment;

import uk.co.cgfindies.diabetestracker.Adapter.ReadingListAdapter;
import uk.co.cgfindies.diabetestracker.Contract.DB;
import uk.co.cgfindies.diabetestracker.DB.ReadingManager;
import uk.co.cgfindies.diabetestracker.R;

/**
 * Provides a List of Reading entities.
 */
public class ReadingListFragment extends ListFragment {

    @InjectDependency
    ReadingManager readingManager;

    /**
     * Set a ReadingListAdapter as the Fragment's ListAdapter when the Activity has been created.
     * {@inheritDoc}
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ReadingListAdapter(getActivity(), readingManager.select().orderBy(DB.ReadingColumn.DATE_LEVEL_TAKEN, false)));
    }
}