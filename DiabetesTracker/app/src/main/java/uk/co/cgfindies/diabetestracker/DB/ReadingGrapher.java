package uk.co.cgfindies.diabetestracker.DB;

import android.content.Context;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.droidparts.persist.sql.stmt.Is;
import org.droidparts.persist.sql.stmt.Select;
import org.droidparts.util.L;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import uk.co.cgfindies.diabetestracker.Contract.DB;
import uk.co.cgfindies.diabetestracker.Model.Reading;

/**
 * Provides Readings graph data for a date period.
 */
public class ReadingGrapher extends ReadingManager
{

    // Used to set the x-axis min / max values
    private long earliestTime = -1;
    private long latestTime = -1;

    public ReadingGrapher(Context ctx)
    {
        super(ctx);
    }

    /**
     * @return The milliseconds since epoch representing the date/time of the earliest Reading in the last set. Defaults to -1
     */
    public long getEarliestTime()
    {
        return earliestTime;
    }

    /**
     * @return The milliseconds since epoch representing the date/time of the latest Reading in the last set. Defaults to -1
     */
    public long getLatestTime()
    {
        return latestTime;
    }

    /**
     * @return Series data for the previous 24 hours.
     */
    public LineGraphSeries<DataPoint> getSeriesForDay()
    {
        return getSeriesFor(Calendar.DAY_OF_MONTH, 1,  null);
    }

    /**
     * @return Series data for the previous week.
     */
    public LineGraphSeries<DataPoint> getSeriesForWeek()
    {
        return getSeriesFor(Calendar.WEEK_OF_YEAR, 1,  null);
    }

    /**
     * @return Series data for the previous Month.
     */
    public LineGraphSeries<DataPoint> getSeriesForMonth()
    {
        return getSeriesFor(Calendar.MONTH, 1,  null);
    }

    /**
     * @return Series data for the previous Year.
     */
    public LineGraphSeries<DataPoint> getSeriesForYear()
    {
        return getSeriesFor(Calendar.YEAR, 1,  null);
    }

    /**
     * Get series data for a custom period.
     *
     * @param period The period, represented by a Calendar period constant
     * @param number The number of periods to go back from the end date.
     * @param endDate A Calendar object representing the end of the date range to collect data for. Use null to default to now.
     * @return The series data, or null if no results were found.
     */
    public LineGraphSeries<DataPoint> getSeriesFor(int period, int number, Calendar endDate)
    {
        // Reset the min/max times.
        earliestTime = -1;
        latestTime = -1;

        // Get the raw data for this date range
        Reading[] readings = getReadingFor(period, number, endDate);

        // No data
        if (readings.length == 0)
        {
            return null;
        }

        int readingLength = readings.length;

        // The series data
        DataPoint[] data = new DataPoint[readingLength];

        // Add each raw data point to the series
        for (int i = 0; i < readingLength; i++)
        {
            data[i] = new DataPoint(readings[i].date_level_taken, readings[i].bloodSugarLevel);
        }

        // Set the min/max times
        earliestTime = readings[0].date_level_taken.getTime();
        latestTime = readings[readingLength-1].date_level_taken.getTime();

        // Return the graph series.
        return new LineGraphSeries<>(data);
    }

    /**
     * Get the raw data Readings[] list for a period
     *
     * @param period The period, represented by a Calendar period constant
     * @param number The number of periods to go back from the end date.
     * @param endDate A Calendar object representing the end of the date range to collect data for. Use null to default to now.
     * @return The raw Reading[] array. If only one data point would be returned, one will be added at the beginning to allow a line to be drawn.
     */
    protected Reading[] getReadingFor(int period, int number, Calendar endDate)
    {
        // Default value for the end date.
        if (endDate == null) {
            endDate = GregorianCalendar.getInstance();
            endDate.setTime(new Date());
        }

        // The start date
        Calendar startDate = (Calendar)endDate.clone();
        startDate.add(period, number * -1);

        // Select all readings between the two dates.
        Select<Reading> select = select()
                .where(DB.ReadingColumn.DATE_LEVEL_TAKEN, Is.BETWEEN, startDate.getTime().getTime(), endDate.getTime().getTime())
                .orderBy(DB.ReadingColumn.DATE_LEVEL_TAKEN, true);
        ArrayList<Reading> readings = readAll(select);

        // Line chart doesn't work with just one element.
        if (readings.size() == 1)
        {
            Reading currentReading = readings.get(0);
            Reading blankReading = currentReading.clone();

            blankReading.bloodSugarLevel = 0;
            blankReading.date_level_taken = new Date(blankReading.date_level_taken.getTime() - 360000);
            readings.add(0, blankReading);
        }

        // Return the data.
        return readings.toArray(new Reading[readings.size()]);
    }
}
