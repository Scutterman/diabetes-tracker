package uk.co.cgfindies.diabetestracker.Activity;

import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import uk.co.cgfindies.diabetestracker.Fragment.AddReadingFragment;
import uk.co.cgfindies.diabetestracker.Fragment.BaseFragment;
import uk.co.cgfindies.diabetestracker.R;

public class BloodSugarActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_add_blood_sugar);
        super.onCreate(savedInstanceState);
    }

    protected void addTabs()
    {
        addTab("add_fragment");
        addTab("list_fragment");
        addTab("graph_fragment");
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

    protected org.droidparts.fragment.support.v4.Fragment getFragmentFromTag(String tag)
    {
        switch (tag)
        {
            case "add_fragment":
                return AddReadingFragment.newInstance();

            case "list_fragment":
                return BaseFragment.newInstance(getString(R.string.fragment_title_list), getString(R.string.feature_coming));

            case "graph_fragment":
                return BaseFragment.newInstance(getString(R.string.fragment_title_graph), getString(R.string.feature_coming));
            default:
                return null;
        }
    }

}
