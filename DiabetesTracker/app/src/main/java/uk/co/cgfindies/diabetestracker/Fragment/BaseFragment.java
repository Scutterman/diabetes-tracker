package uk.co.cgfindies.diabetestracker.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.droidparts.annotation.inject.InjectView;
import org.droidparts.fragment.support.v4.Fragment;

import uk.co.cgfindies.diabetestracker.R;

/**
 * Created by Scutterman on 08/04/2016.
 */
/**
 * A placeholder fragment containing a simple view.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    /**
     * The fragment argument representing the fragment title for this fragment
     */
    private static final String ARG_FRAGMENT_TITLE = "fragment_title";

    /**
     * The fragment argument representing the fragment title for this fragment
     */
    private static final String ARG_FRAGMENT_TEXT = "fragment_text";

    @InjectView(id = R.id.section_label)
    private TextView sectionLabel;

    @InjectView(id = R.id.section_text)
    private TextView sectionText;

    @Override
    protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_add_blood_sugar, null);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        Bundle args = getArguments();
        if (args != null)
        {
            if (args.containsKey(ARG_FRAGMENT_TITLE))
            {
                sectionLabel.setText(args.getString(ARG_FRAGMENT_TITLE));
            }

            if (args.containsKey(ARG_FRAGMENT_TEXT))
            {
                sectionText.setText(args.getString(ARG_FRAGMENT_TEXT));
            }
        }


    }

    @Override
    public void onClick(View v)
    {
        // Override to use
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BaseFragment newInstance(String fragmentTitle, String fragmentText) {
        Bundle args = new Bundle();
        args.putString(ARG_FRAGMENT_TITLE, fragmentTitle);
        args.putString(ARG_FRAGMENT_TEXT, fragmentText);

        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
