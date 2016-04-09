package uk.co.cgfindies.diabetestracker.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.droidparts.annotation.inject.InjectView;
import org.droidparts.widget.ClearableEditText;

import java.util.Date;

import uk.co.cgfindies.diabetestracker.DB.ReadingManager;
import uk.co.cgfindies.diabetestracker.R;
import uk.co.cgfindies.diabetestracker.Model.Reading;

public class AddReadingFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(id = R.id.add_reading_button)
    private Button addButton;

    @InjectView(id = R.id.add_reading_value)
    private ClearableEditText readingValueField;

    @InjectView(id = R.id.add_reading_date_picker)
    private DatePicker readingDate;

    @InjectView(id = R.id.add_reading_time_picker)
    private TimePicker readingTime;

    public static AddReadingFragment newInstance(String param1, String param2) {
        return new AddReadingFragment();
    }

    @Override
    protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_add_reading, container, false);
    }

    @Override
    public void onClick(View v)
    {
        if (v == addButton)
        {
            String readingValue = readingValueField.getText().toString().trim();

            if (readingValue == null || readingValue.isEmpty())
            {
                // Validation
            }
            else
            {
                try {
                    double readingNumber = Double.parseDouble(readingValue);
                    Reading reading = new Reading();
                    reading.bloodSugarLevel = readingNumber;



                    ReadingManager manager = new ReadingManager(getActivity());
                    manager.create(reading);
                }
                catch (NumberFormatException e)
                {
                    // Validation
                }
            }
        }
    }

}
