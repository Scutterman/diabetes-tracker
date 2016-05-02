package uk.co.cgfindies.diabetestracker.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.droidparts.adapter.widget.StringSpinnerAdapter;
import org.droidparts.annotation.inject.InjectView;
import org.droidparts.util.L;

import uk.co.cgfindies.diabetestracker.DB.ReadingGrapher;
import uk.co.cgfindies.diabetestracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewGraphsFragment extends BaseFragment implements AdapterView.OnItemSelectedListener
{
    StringSpinnerAdapter spinnerAdapter;

    @InjectView(id = R.id.graph_period_spinner)
    Spinner spinner;

    @InjectView(id = R.id.view_graph_day)
    GraphView readingGraph;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        super.onCreateView(savedInstanceState, inflater, container);
        return inflater.inflate(R.layout.fragment_view_graphs, null);
    }

    /**
     * Set up the Spinner and the Graph
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setupSpinner();

        readingGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        GridLabelRenderer labels = readingGraph.getGridLabelRenderer();
        labels.setHorizontalAxisTitle(getString(R.string.graph_x_axis_label));
        labels.setVerticalAxisTitle(getString(R.string.graph_y_axis_label));
    }

    /**
     * Draw the correct graph when a Spinner item has been chosen.
     * {@inheritDoc}
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        setupGraphs(getKeyFromGraphSpinner(position));
    }

    /**
     * Reset the view when nothing has been selected.
     * {@inheritDoc}
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        clearErrors();
        readingGraph.removeAllSeries();
    }

    /**
     * Set up the Spinner and the SpinnerAdapter
     */
    public void setupSpinner()
    {
        if (spinnerAdapter == null)
        {
            spinnerAdapter = new StringSpinnerAdapter(spinner, R.array.graph_period_spinner_values);
        }

        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinnerAdapter);
    }

    /**
     * Set up the graph when a period has been chosen from the spinner.
     * @param selectedPeriod One of the values from R.array.graph_period_spinner_keys that relates to the values from R.array.graph_period_spinner_values
     */
    public void setupGraphs(String selectedPeriod)
    {
        // Reset the view
        clearErrors();
        readingGraph.removeAllSeries();

        ReadingGrapher grapher = new ReadingGrapher(getContext());
        LineGraphSeries<DataPoint> series = null;

        // Get the correct series for the selected period
        switch (selectedPeriod)
        {
            case "day":
                series = grapher.getSeriesForDay();
            break;
            case "week":
                series = grapher.getSeriesForWeek();
            break;
            case "month":
                series = grapher.getSeriesForMonth();
            break;
            case "year":
                series = grapher.getSeriesForYear();
            break;
        }

        // Success!
        if (series != null)
        {
            readingGraph.addSeries(series);

            // set manual x bounds to have nice steps
            readingGraph.getViewport().setMinX(grapher.getEarliestTime());
            readingGraph.getViewport().setMaxX(grapher.getLatestTime());
            readingGraph.getViewport().setXAxisBoundsManual(true);
        }
        else
        {
            addError(getString(R.string.graph_no_data));
        }
    }

    /**
     * Get the value from R.array.graph_period_spinner_keys that relates to a specific index.
     * @param selectedIndex The index to fetch the key for
     * @return The key
     */
    protected String getKeyFromGraphSpinner(int selectedIndex)
    {
        String[] keys = getResources().getStringArray(R.array.graph_period_spinner_keys);
        return keys[selectedIndex];
    }
}
