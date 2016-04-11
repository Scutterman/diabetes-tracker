package uk.co.cgfindies.diabetestracker.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.droidparts.annotation.inject.InjectView;
import org.droidparts.fragment.support.v4.Fragment;
import org.droidparts.util.L;

import uk.co.cgfindies.diabetestracker.Activity.BaseActivity;
import uk.co.cgfindies.diabetestracker.R;

/**
 * Created by Scutterman on 08/04/2016.
 */
/**
 * A placeholder fragment containing a simple view.
 */
public class BaseFragment extends Fragment {

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

    public void clearErrors()
    {
        L.w("Clearing errors from BaseFragment");
        Activity activity = getActivity();
        if (activity instanceof BaseActivity)
        {
            L.w("Activity found");
            ((BaseActivity) activity).clearErrors();
        }
    }

    public void addError(String errorMessage)
    {
        L.w("Adding error from BaseFragment " + errorMessage);
        Activity activity = getActivity();
        if (activity instanceof BaseActivity)
        {
            L.w("Activity found");
            ((BaseActivity) activity).addError(errorMessage);
        }
    }
}
