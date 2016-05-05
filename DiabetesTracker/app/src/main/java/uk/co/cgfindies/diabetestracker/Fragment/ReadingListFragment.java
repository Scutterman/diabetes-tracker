package uk.co.cgfindies.diabetestracker.Fragment;

import android.os.Bundle;

import org.droidparts.annotation.inject.InjectDependency;
import org.droidparts.fragment.support.v4.ListFragment;

import uk.co.cgfindies.diabetestracker.Adapter.ReadingListAdapter;
import uk.co.cgfindies.diabetestracker.Contract.DB;
import uk.co.cgfindies.diabetestracker.DB.ReadingManager;

/**
 * Provides a List of Reading entities.
 */
public class ReadingListFragment extends ListFragment implements BaseFragment.FragmentSelectedListener {

    @InjectDependency
    private ReadingManager readingManager;

    private ReadingListAdapter adapter;

    /**
     * Set a ReadingListAdapter as the Fragment's ListAdapter when the Activity has been created.
     * {@inheritDoc}
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        adapter = new ReadingListAdapter(getActivity(), readingManager.select().orderBy(DB.ReadingColumn.DATE_LEVEL_TAKEN, false));
        setListAdapter(adapter);
    }

    @Override
    public void onFragmentSelected()
    {
        refresh();
    }

    /**
     * Refresh the ListView when the data changes.
     */
    public void refresh()
    {
        if (adapter != null)
        {
            adapter.requeryData();
            adapter.notifyDataSetChanged();
        }
    }

}
