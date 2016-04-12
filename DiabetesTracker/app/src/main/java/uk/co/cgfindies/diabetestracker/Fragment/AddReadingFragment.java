package uk.co.cgfindies.diabetestracker.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.droidparts.annotation.inject.InjectResource;
import org.droidparts.annotation.inject.InjectView;
import org.droidparts.annotation.serialize.SaveInstanceState;
import org.droidparts.util.L;
import org.droidparts.widget.ClearableEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import uk.co.cgfindies.diabetestracker.DB.ReadingManager;
import uk.co.cgfindies.diabetestracker.R;
import uk.co.cgfindies.diabetestracker.Model.Reading;

/**
 * Provide a fragment that can be used to input data to add to the Reading table.
 */
public class AddReadingFragment extends BaseFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
    /**
     * The button used to add the entry
     */
    @InjectView(id = R.id.add_reading_button, click = true)
    private Button addButton;

    /**
     * The blood sugar level reading
     */
    @InjectView(id = R.id.add_reading_value)
    private ClearableEditText readingValueField;

    /**
     * The date / time that the blood sugar level reading was taken
     */
    @InjectView(id = R.id.add_reading_date_field, click = true)
    private TextView readingDate;

    /**
     * Allows storing and manipulating the date / time that the blood sugar level was taken
     */
    @SaveInstanceState
    private Calendar dateTakenCalendar;

    /**
     * @return a new instance of this class
     */
    public static AddReadingFragment newInstance()
    {
        return new AddReadingFragment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_add_reading, container, false);
    }

    /**
     * If there is no saved Calendar, create one at the current date / time.
     * Format the value in the Calendar to display in the readingDate TextView

     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if (dateTakenCalendar == null)
        {
            dateTakenCalendar = GregorianCalendar.getInstance();
            dateTakenCalendar.setTime(new Date());
        }

        setDateTakenField();
    }

    /**
     * Handle click events. We're interested in if the user clicks on the Add button, or the Reading Date view.
     */
    @Override
    public void onClick(View v)
    {

        if (v == addButton)
        {
            addReading();
        }
        else if (v == readingDate)
        {
            showDatePicker();
        }
    }

    /**
     * Update the Calendar when the user updates the date using the DatePicker, then show the TimePicker
     * {@inheritDoc}
     */
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        dateTakenCalendar.set(year, monthOfYear, dayOfMonth);

        setDateTakenField();

        showTimePicker();
    }

    /**
     * Update the Calendar when the user updates the tome using the TimePicker
     * {@inheritDoc}
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        dateTakenCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateTakenCalendar.set(Calendar.MINUTE, minute);

        setDateTakenField();
    }

    /**
     * Format the Calendar value and display it in the readingDate View.
     */
    private void setDateTakenField()
    {
        DateFormat formatter = SimpleDateFormat.getInstance();
        readingDate.setText(formatter.format(dateTakenCalendar.getTime()));
    }

    /**
     * Validate the form input values and attempt to insert a Reading.
     */
    private void addReading()
    {
        // Clear messages and errors
        clearMessages();
        clearErrors();

        // Track whether a validation error occurred
        boolean error = false;

        // The Reading entity used to insert the database entry
        Reading reading = new Reading();

        // The blood sugar reading
        String readingValue = readingValueField.getText().toString().trim();

        // No blood sugar reading supplied
        if (readingValue == null || readingValue.isEmpty())
        {
            addError(getString(R.string.add_reading_value_required));
            error = true;
        }

        // Make sure that the readingValue is a number
        try {
            double readingNumber = Double.parseDouble(readingValue);
            reading.bloodSugarLevel = readingNumber;

            reading.date_level_taken = dateTakenCalendar.getTime();

        }
        catch (NumberFormatException e)
        {
            addError(getString(R.string.add_reading_value_nan));
            error = true;
        }

        // Validation failed, end before attempting to insert.
        if (error == true)
        {
            return;
        }

        // Attempt to insert the Reading
        ReadingManager manager = new ReadingManager(getActivity());
        if (manager.create(reading) == true)
        {
            // Success
            addMessage(getString(R.string.add_reading_insert_successful));
        }
        else
        {
            // Failure
            addError(getString(R.string.add_reading_unknown_error));
        }
    }

    /**
     * Show the DatePickerDialog that allows the date portion of the readingDate to be set.
     */
    private void showDatePicker()
    {
        new DatePickerDialog(
                getActivity(),
                this,
                dateTakenCalendar.get(Calendar.YEAR),
                dateTakenCalendar.get(Calendar.MONTH),
                dateTakenCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();

    }

    /**
     * Show a TimePickerDialog that allows the time portion of the readingDate to be set.
     */
    private void showTimePicker()
    {
        new TimePickerDialog(
                getActivity(),
                this,
                dateTakenCalendar.get(Calendar.HOUR_OF_DAY),
                dateTakenCalendar.get(Calendar.MINUTE),
                true
        ).show();

    }

}
