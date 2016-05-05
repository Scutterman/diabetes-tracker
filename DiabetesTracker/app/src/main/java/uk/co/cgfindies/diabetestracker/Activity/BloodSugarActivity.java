package uk.co.cgfindies.diabetestracker.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import uk.co.cgfindies.diabetestracker.Fragment.AddReadingFragment;
import uk.co.cgfindies.diabetestracker.Fragment.BaseFragment;
import uk.co.cgfindies.diabetestracker.Fragment.ReadingListFragment;
import uk.co.cgfindies.diabetestracker.Fragment.ViewGraphsFragment;
import uk.co.cgfindies.diabetestracker.R;

/**
 * Provide an Activity to manage blood sugar levels.
 */
public class BloodSugarActivity extends BaseActivity
{
    private static final String TAG_ADD = "add_fragment";
    private static final String TAG_LIST = "list_fragment";
    private static final String TAG_GRAPH = "graph_fragment";

    private Fragment addFragment;
    private Fragment listFragment;
    private Fragment graphFragment;


    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_add_blood_sugar);
        super.onCreate(savedInstanceState);
    }

    /**
     * Add the tabs from this activity by specifying tags.
     */
    @Override
    protected void addTabs()
    {
        addTab(TAG_ADD);
        addTab(TAG_LIST);
        addTab(TAG_GRAPH);
    }

    /**
     * Get a Tab
     *
     * @param tag Used to specify what Tab you want to return
     * @return The Tab
     */
    @Override
    protected ActionBar.Tab getTabFromTag(String tag)
    {
        switch (tag)
        {
            case TAG_ADD:
                return getSupportActionBar().newTab().setText(R.string.tab_name_add).setTag(tag);

            case TAG_LIST:
                return getSupportActionBar().newTab().setText(R.string.tab_name_list).setTag(tag);

            case TAG_GRAPH:
                return getSupportActionBar().newTab().setText(R.string.tab_name_graph).setTag(tag);
            default:
                return null;
        }
    }

    /**
     * Get a Fragment to associate with a Tab
     *
     * @param tag Used to specify what Fragment you want to return
     * @return The Fragment
     */
    @Override
    protected Fragment getFragmentFromTag(String tag)
    {
        switch (tag)
        {
            case TAG_ADD:
                if (addFragment == null)
                {
                    addFragment = AddReadingFragment.newInstance();
                }
                return addFragment;

            case TAG_LIST:
                if (listFragment == null)
                {
                    listFragment = new ReadingListFragment();
                }
                return listFragment;

            case TAG_GRAPH:
                if (graphFragment == null)
                {
                    graphFragment = new ViewGraphsFragment();
                }
                return graphFragment;
            default:
                return null;
        }
    }

    /**
     * Refresh the ListView when the tab is changed and the ListView is now showing.
     * {@inheritDoc}
     */
    @Override
    public void onTabChanged(int position)
    {
        super.onTabChanged(position);

        ActionBar.Tab tab = getSupportActionBar().getSelectedTab();
        String tag = (String)tab.getTag();
        Fragment fragment = getFragmentFromTag(tag);

        if (fragment instanceof BaseFragment.FragmentSelectedListener)
        {
            ((BaseFragment.FragmentSelectedListener) fragment).onFragmentSelected();
        }
    }

}
