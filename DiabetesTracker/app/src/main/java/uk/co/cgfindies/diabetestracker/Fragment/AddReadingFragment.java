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
import android.widget.TimePicker;

import org.droidparts.annotation.inject.InjectView;
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

public class AddReadingFragment extends BaseFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
    @InjectView(id = R.id.add_reading_button, click = true)
    private Button addButton;

    @InjectView(id = R.id.add_reading_value)
    private ClearableEditText readingValueField;

    @InjectView(id = R.id.add_reading_date_field, click = true)
    private EditText readingDate;

    private Calendar dateTakenCalendar;

    public static AddReadingFragment newInstance()
    {
        return new AddReadingFragment();
    }

    @Override
    protected View onCreateView(Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_add_reading, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        dateTakenCalendar = getReadingTakenCalendar();

        if (savedInstanceState == null)
        {
            setDateTakenField();
        }

    }

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

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        dateTakenCalendar.set(year, monthOfYear, dayOfMonth);

        setDateTakenField();

        showTimePicker();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        dateTakenCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateTakenCalendar.set(Calendar.MINUTE, minute);

        setDateTakenField();
    }

    private void setDateTakenField()
    {
        DateFormat formatter = SimpleDateFormat.getInstance();
        readingDate.setText(formatter.format(dateTakenCalendar.getTime()));
    }

    private Calendar getReadingTakenCalendar()
    {
        Date dateTaken;
        String dateTakenString = readingValueField.getText().toString().trim();

        if (dateTakenString != null && dateTakenString.isEmpty() == false)
        {
            try {
                dateTaken = SimpleDateFormat.getDateTimeInstance().parse(dateTakenString);
            }
            catch (ParseException e){
                dateTaken = new Date();
            }
        }
        else
        {
            dateTaken = new Date();
        }

        Calendar dateTakenCalendar = GregorianCalendar.getInstance();
        dateTakenCalendar.setTime(dateTaken);

        return dateTakenCalendar;
    }

    private void addReading()
    {
        clearErrors();
        String readingValue = readingValueField.getText().toString().trim();

        if (readingValue == null || readingValue.isEmpty())
        {
            addError("Please add a reading.");
        }
        else
        {
            try {
                double readingNumber = Double.parseDouble(readingValue);
                Reading reading = new Reading();
                reading.bloodSugarLevel = readingNumber;

                reading.date_level_taken = dateTakenCalendar.getTime();

                ReadingManager manager = new ReadingManager(getActivity());
                boolean status = manager.create(reading);

                if (status == true)
                {
                    addError("Reading added");
                }
                else
                {
                    addError("Error adding reading, please try again.");
                }
            }
            catch (NumberFormatException e)
            {
                addError("The reading must be a number.");
            }
        }
    }

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
