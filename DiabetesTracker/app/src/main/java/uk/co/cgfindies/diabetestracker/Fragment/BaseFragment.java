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
 * Provides methods that will be useful to every Fragment in the app.
 */
public class BaseFragment extends Fragment {

    /**
     * Implement for when a fragment's tab is selected.
     */
    public interface FragmentSelectedListener {
        public void onFragmentSelected();
    }

    /**
     * The fragment argument representing the fragment title for this fragment
     */
    private static final String ARG_FRAGMENT_TITLE = "fragment_title";

    /**
     * The fragment argument representing the fragment title for this fragment
     */
    private static final String ARG_FRAGMENT_TEXT = "fragment_text";

    /**
     * Somewhere to add messages to.
     */
    @InjectView(id=R.id.message_box)
    private ViewGroup messageBox;

    /**
     * Somewhere to add errors to.
     */
    @InjectView(id=R.id.error_box)
    private ViewGroup errorBox;

    /**
     * Placeholder title for the Fragment.
     */
    @InjectView(id = R.id.section_label)
    private TextView sectionLabel;

    /**
     * Placeholder description for the Fragment.
     */
    @InjectView(id = R.id.section_text)
    private TextView sectionText;

    /**
     * {@inheritDoc}
     */
    @Override
    protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_add_blood_sugar, null);
    }

    /**
     * Add the placeholder title and description, if they have been provided and there's somewhere to put them.
     * @param bundle Unused here, passed to the super method.
     */
    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        Bundle args = getArguments();
        if (args != null)
        {
            if (sectionLabel != null && args.containsKey(ARG_FRAGMENT_TITLE))
            {
                sectionLabel.setText(args.getString(ARG_FRAGMENT_TITLE));
            }

            if (sectionText != null && args.containsKey(ARG_FRAGMENT_TEXT))
            {
                sectionText.setText(args.getString(ARG_FRAGMENT_TEXT));
            }
        }


    }

    /**
     * Create an instance of this Fragment that can be used as a placeholder.
     *
     * @param fragmentTitle The title of the placeholder Fragment
     * @param fragmentText The description of the placeholder Fragment
     * @return the Fragment instance
     */
    public static BaseFragment newInstance(String fragmentTitle, String fragmentText) {
        Bundle args = new Bundle();
        args.putString(ARG_FRAGMENT_TITLE, fragmentTitle);
        args.putString(ARG_FRAGMENT_TEXT, fragmentText);

        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Remove all messages from the messageBox ViewGroup
     */
    public void clearMessages()
    {
        if (messageBox != null)
        {
            messageBox.removeAllViews();
        }
    }

    /**
     * Add a message to the messageBox ViewGroup
     * @param message The message to add
     */
    public void addMessage(String message)
    {
        if (messageBox != null)
        {

            TextView messageView = (TextView)getActivity().getLayoutInflater().inflate(R.layout.message_item, null);
            messageView.setText(message);
            messageBox.addView(messageView);
        }
    }

    /**
     * Remove all errors from the errorBox ViewGroup
     */
    public void clearErrors()
    {
        if (errorBox != null)
        {
            errorBox.removeAllViews();
        }
    }

    /**
     * Add an error to the errorBox ViewGroup
     * @param errorMessage The error to add
     */
    public void addError(String errorMessage)
    {
        if (errorBox != null)
        {
            TextView errorMessageView = (TextView)getActivity().getLayoutInflater().inflate(R.layout.error_item, null);
            errorMessageView.setText(errorMessage);
            errorBox.addView(errorMessageView);
        }
    }

}
