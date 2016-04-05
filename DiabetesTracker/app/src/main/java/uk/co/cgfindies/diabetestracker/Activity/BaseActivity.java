package uk.co.cgfindies.diabetestracker.Activity;

import org.droidparts.activity.support.v7.TabbedAppCompatActivity;
import org.droidparts.fragment.support.v4.Fragment;

import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import uk.co.cgfindies.diabetestracker.R;

/**
 * Created by Scutterman on 04/04/2016.
 */
public class BaseActivity extends TabbedAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();

        ActionBar.Tab tab = actionBar.newTab().setText("Tab 1");
        Fragment fragment = PlaceholderFragment.newInstance(0);
        addTab(tab, fragment);

        ActionBar.Tab tab2 = actionBar.newTab().setText("Tab 2");
        Fragment fragment2 = PlaceholderFragment.newInstance(1);
        addTab(tab2, fragment2);

        ActionBar.Tab tab3 = actionBar.newTab().setText("Tab 3");
        Fragment fragment3 = PlaceholderFragment.newInstance(2);
        addTab(tab3, fragment3);

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
         /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        @Override
        protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container) {
            return inflater.inflate(R.layout.fragment_add_blood_sugar, null);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

    }
}
