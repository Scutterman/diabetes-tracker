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
import android.widget.TextView;

import uk.co.cgfindies.diabetestracker.R;

/**
 * Created by Scutterman on 04/04/2016.
 */
public class BaseActivity extends TabbedAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        // Override to use.
    }

    /**
     * BloodSugarActivity a tab to the actionbar
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
        // Override to use
        return null;
    }

    protected Fragment getFragmentFromTag(String tag)
    {
        // Override to use
        return null;
    }

}
