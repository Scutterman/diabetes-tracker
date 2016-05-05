package uk.co.cgfindies.diabetestracker.Activity;

import org.droidparts.activity.support.v7.TabbedAppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import uk.co.cgfindies.diabetestracker.R;

/**
 * Provides methods that will be useful to every activity in the app.
 */
public class BaseActivity extends TabbedAppCompatActivity implements View.OnClickListener {

    // Store a list of all Fragments that implement OnClickListener
    private List<View.OnClickListener> clicks = new ArrayList<>();

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
        // Iterate over all of the Fragments that have an OnClickListener
        for (View.OnClickListener listener : clicks)
        {
            // If the Fragment is still available, bubble the click event to it
            if (listener != null) {
                listener.onClick(v);
            }
        }
    }

    /**
     * Close / hide the keyboard when the tab is changed.
     *
     * {@inheritDoc}
     */
    @Override
    protected void onTabChanged(int position) {
        super.onTabChanged(position);
        closeKeyboard(this);
    }

    /**
     * Add a tab to the actionbar, and add the corresponding fragment to the main_content ViewGroup
     *
     * @param tag The tag of the tab, also used to work out what content the tab has.
     */
    protected void addTab(String tag)
    {
        addTab(tag, R.id.main_content);
    }

    /**
     * Add a tab to the actionbar.
     * The tab is provided by overriding getTabFromTag()
     * The fragment is provided by overriding getFragmentFromTag()
     *
     * @param tag The tag of the tab, also used to work out what content the tab has.
     * @param container The id of a ViewGroup to add the tab too.
     */
    protected void addTab(String tag, int container)
    {
        // Get the Tab
        @SuppressWarnings("deprecation") ActionBar.Tab tab = getTabFromTag(tag);

        // Get the Fragment, if it's already been added to the ViewGroup
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);

        // Get the Fragment and add it to the ViewGroup if it wasn't found
        if (fragment == null)
        {
            fragment = getFragmentFromTag(tag);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(container, fragment, tag).commit();
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
    @SuppressWarnings("deprecation")
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

    /**
     * Close/hide the soft keyboard, if it is showing.
     *
     * @param activity An Activity representing the Activity / InputMethodManager
     */
    public static void closeKeyboard(final Activity activity)
    {
        View focussedView = activity.getWindow().getDecorView();
        if (focussedView == null)
        {
            focussedView = new View(activity);
        }

        focussedView.clearFocus();

        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(focussedView.getWindowToken(), 0);
    }

}
