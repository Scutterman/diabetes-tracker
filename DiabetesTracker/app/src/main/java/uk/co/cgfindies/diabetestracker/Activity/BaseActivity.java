package uk.co.cgfindies.diabetestracker.Activity;

import org.droidparts.activity.support.v7.TabbedAppCompatActivity;
import org.droidparts.annotation.inject.InjectView;
import org.droidparts.fragment.support.v4.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import uk.co.cgfindies.diabetestracker.R;

/**
 * Created by Scutterman on 04/04/2016.
 */
public class BaseActivity extends TabbedAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_blood_sugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void addTabs()
    {
        addTab("add_fragment");
        addTab("list_fragment");
        addTab("graph_fragment");
    }

    /**
     * Add a tab to the actionbar
     *
     * @param tag String The tag of the tab, also used to work out what content the tab has.
     */
    protected void addTab(String tag)
    {
        ActionBar.Tab tab = getTabFromTag(tag);

        Fragment fragment = (Fragment)(getSupportFragmentManager().findFragmentByTag(tag));

        if (fragment == null)
        {
            fragment = getFragmentFromTag(tag);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_content, fragment, tag).commit();
        }

        if (fragment != null && tab != null)
        {
            addTab(tab, fragment);
        }
    }

    protected ActionBar.Tab getTabFromTag(String tag)
    {
        switch (tag)
        {
            case "add_fragment":
                return getSupportActionBar().newTab().setText(R.string.tab_name_add);

            case "list_fragment":
                return getSupportActionBar().newTab().setText(R.string.tab_name_list);

            case "graph_fragment":
                return getSupportActionBar().newTab().setText(R.string.tab_name_graph);
            default:
                return null;
        }
    }

    protected Fragment getFragmentFromTag(String tag)
    {
        switch (tag)
        {
            case "add_fragment":
                return PlaceholderFragment.newInstance(0, getString(R.string.fragment_title_add));

            case "list_fragment":
                return PlaceholderFragment.newInstance(1, getString(R.string.fragment_title_list));

            case "graph_fragment":
                return PlaceholderFragment.newInstance(2, getString(R.string.fragment_title_graph));
            default:
                return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
         /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * The fragment argument representing the fragment title for this fragment
         */
        private static final String ARG_FRAGMENT_TITLE = "fragment_title";

        @InjectView(id = R.id.section_label)
        private TextView sectionLabel;

        @InjectView(id = R.id.section_text)
        private TextView sectionText;

        @Override
        protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container) {
            return inflater.inflate(R.layout.fragment_add_blood_sugar, null);
        }

        @Override
        public void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);
            sectionLabel.setText(getArguments().getString(ARG_FRAGMENT_TITLE));
            sectionText.setText(R.string.feature_coming);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, String fragmentTitle) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_FRAGMENT_TITLE, fragmentTitle);
            fragment.setArguments(args);
            return fragment;
        }

    }
}
