package softgroup.ua.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Stanisalv Rymar
 */

/**
 * DateParser created for simple convert Calendar object to String, and to another direction
 */
public class DateParser {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     *
     * @param calendarDate
     * @return string representation Calendar object
     */
    public String calendarToString(Calendar calendarDate) {
        return simpleDateFormat.format(calendarDate.getTime());
    }

    /**
     *
     * @param stringDate in format like dd-MM-yyyy
     * @return Calendar object
     * @throws ParseException
     */
    public Calendar stringToCalendar(String stringDate) throws ParseException {
        simpleDateFormat.parse(stringDate);
        return simpleDateFormat.getCalendar();
    }
}
