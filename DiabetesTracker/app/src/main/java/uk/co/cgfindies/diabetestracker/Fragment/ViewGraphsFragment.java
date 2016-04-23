package uk.co.cgfindies.diabetestracker.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import org.droidparts.adapter.widget.StringSpinnerAdapter;
import org.droidparts.annotation.inject.InjectView;
import org.droidparts.annotation.serialize.SaveInstanceState;
import org.droidparts.util.L;

import uk.co.cgfindies.diabetestracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewGraphsFragment extends BaseFragment implements AdapterView.OnItemSelectedListener
{
    StringSpinnerAdapter spinnerAdapter;

    @InjectView(id = R.id.graph_period_spinner)
    Spinner spinner;

    @Override
    public View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        super.onCreateView(savedInstanceState, inflater, container);
        return inflater.inflate(R.layout.fragment_view_graphs, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (spinnerAdapter == null)
        {
            spinnerAdapter = new StringSpinnerAdapter(spinner, R.array.graph_period_spinner_values);
        }

        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        L.w(spinnerAdapter.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
