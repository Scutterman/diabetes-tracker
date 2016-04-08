package uk.co.cgfindies.diabetestracker.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.droidparts.annotation.inject.InjectView;
import org.droidparts.fragment.support.v4.Fragment;

import uk.co.cgfindies.diabetestracker.R;

public class AddReadingFragment extends BaseFragment {
    @InjectView(id = R.id.add_reading_button)
    private Button addButton;

    public static AddReadingFragment newInstance(String param1, String param2) {
        return new AddReadingFragment();
    }

    @Override
    protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_add_reading, container, false);
    }

}
