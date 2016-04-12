package uk.co.cgfindies.diabetestracker.Activity;

import org.droidparts.activity.support.v7.TabbedAppCompatActivity;
import org.droidparts.annotation.inject.InjectView;
import org.droidparts.fragment.support.v4.Fragment;
import org.droidparts.util.L;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uk.co.cgfindies.diabetestracker.R;

/**
 * Provides methods that will be useful to every activity in the app.
 */
public class BaseActivity extends TabbedAppCompatActivity implements View.OnClickListener {

    // Store a list of all Fragments that implement OnClickListener
    private List<View.OnClickListener> clicks = new ArrayList<View.OnClickListener>();

    /**
     * Create the Activity and trigger adding the tags.
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addTabs();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_blood_sugar, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    /**
     * When a Fragment with an OnClickListener is attached, add it to the list of OnClickListeners.
     * @param fragment The fragment that was attached.
     */
    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment)
    {
        super.onAttachFragment(fragment);

        if (fragment instanceof View.OnClickListener)
        {
            clicks.add((View.OnClickListener)fragment);
        }
    }

    /**
     * When a click happens, see if there are any active fragments that can handle the click.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        Iterator<View.OnClickListener> it = clicks.iterator();

        // Iterate over all of the Fragments that have an OnClickListener
        while (it.hasNext())
        {
            View.OnClickListener listener = it.next();

            // If the Fragment is still available, bubble the click event to it
            if (listener != null) {
                listener.onClick(v);
            }
        }
    }

    /**
     * Add a tab to the actionbar.
     * The tab is provided by overriding getTabFromTag()
     * The fragment is provided by overriding getFragmentFromTag()
     * Fragments are automatically added to the ViewGroup R.id.main_content
     *
     * @param tag The tag of the tab, also used to work out what content the tab has.
     */
    protected void addTab(String tag)
    {
        // Get the Tab
        ActionBar.Tab tab = getTabFromTag(tag);

        // Get the Fragment, if it's already been added to the ViewGroup
        Fragment fragment = (Fragment)(getSupportFragmentManager().findFragmentByTag(tag));

        // Get the Fragment and add it to the ViewGroup if it wasn't found
        if (fragment == null)
        {
            fragment = getFragmentFromTag(tag);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_content, fragment, tag).commit();
        }

        // If we found a Fragment and a Tab, add it to the tab bar.
        if (tab != null && fragment != null)
        {
            addTab(tab, fragment);
        }
    }

    /**
     * Called automatically by onCreate()
     * Override and put one or more calls to addTab() inside.
     */
    protected void addTabs() { }

    /**
     * Called by addTab(String tag)
     * Override to use
     *
     * @param tag Used to specify what Tab you want to return
     * @return A Tab based on the specified tag
     */
    protected ActionBar.Tab getTabFromTag(String tag)
    {
        return null;
    }

    /**
     * Called by addTab(String tag)
     * Override to use
     *
     * @param tag Used to specify what Fragment you want to return
     * @return a Fragment based on the specified tag.
     */
    protected Fragment getFragmentFromTag(String tag)
    {
        // Override to use
        return null;
    }

}
