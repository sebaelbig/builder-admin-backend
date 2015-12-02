package ar.com.builderadmin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date Utils and Helper methods.
 * 
 * @author sgarcia
 * 
 */
public class DateUtils {

	/**
	 * Static Date Formats.
	 */
	private final static DateFormat DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy");
	private final static DateFormat DATETIME_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss.SSS");
	private final static DateFormat DATETIME_FORMAT_WITHOUT_MILLISECONDS = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");
	private final static DateFormat DATETIME_FORMAT_WITHOUT_SECONDS_AND_YEAR = new SimpleDateFormat(
			"dd/MM HH:mm");
	private final static DateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");

	/**
	 * Time constants.
	 */
	private final static Long MILLIS_IN_SECOND = 1000L;
	private final static Long SECONDS_IN_MINUTE = 60L;

	/**
	 * WS Date Format.
	 */
	private final static DateFormat SMS_WS_DATE_FORMAT = DATETIME_FORMAT;

	/**
	 * Zeroes the hour part of a date.
	 * 
	 * @param date
	 *            the to use
	 * 
	 * @return date with 0 in hour fields
	 */
	public static Date zeroHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * Parses a String of format "dd/MM/yyyy" to a date
	 * 
	 * @param f
	 *            string to parse
	 * 
	 * @return Date
	 */
	public static Date parseDate(String f) {
		Date d = new Date();
		try {
			d = DATE_FORMAT.parse(f);
		} catch (ParseException e) {
		}

		return d;
	}

	/**
	 * Parses a String of format "dd/MM/yyyy HH:mm:ss.SSS" to a date
	 * 
	 * @param f
	 *            string to parse
	 * 
	 * @return Date
	 */
	public static Date parseDateTime(String f) {
		Date d = new Date();
		try {
			d = DATETIME_FORMAT.parse(f);
		} catch (ParseException e) {
		}

		return d;
	}

	/**
	 * Parses a String of format "HH:mm" to a Date
	 * 
	 * @param h
	 *            string to parse
	 * 
	 * @return Date
	 */
	public static Date parseHour(String h) {
		Date d = new Date();
		try {
			d = HOUR_FORMAT.parse(h);
		} catch (ParseException e) {
		}

		return d;
	}

	/**
	 * Parses a String with format "dd/MM/yyyy HH:mm:ss" to a Date
	 * 
	 * @param h
	 *            string to parse
	 * 
	 * @return Date
	 */
	public static Date parseDateTimeWithoutMilliseconds(String h) {
		Date d = new Date();
		try {
			d = DATETIME_FORMAT_WITHOUT_MILLISECONDS.parse(h);
		} catch (ParseException e) {
		}

		return d;
	}

	/**
	 * Parses a String with format used from the SMS gateway
	 * 
	 * @param h
	 *            string to parse
	 * 
	 * @return Date
	 */
	public static Date parseSmsWsDate(String h) {
		Date d = new Date();
		try {
			d = SMS_WS_DATE_FORMAT.parse(h);
		} catch (ParseException e) {
		}

		return d;
	}

	/**
	 * Formats a Date using "dd/MM/yyyy"
	 * 
	 * @param f
	 *            Date to format
	 * 
	 * @return String
	 */
	public static String formateDate(Date f) {
		if (f == null) {
			f = new Date();
		}
		return DATE_FORMAT.format(f);
	}

	/**
	 * Formats a Date using "dd/MM/yyyy HH:mm:sss.SSS"
	 * 
	 * @param f
	 *            Date to format
	 * 
	 * @return String
	 */
	public static String formateDateTime(Date f) {
		if (f == null) {
			f = new Date();
		}
		return DATETIME_FORMAT.format(f);
	}

	/**
	 * Formats a Date using "HH:mm"
	 * 
	 * @param f
	 *            Date to format
	 * 
	 * @return String
	 */
	public static String formatHour(Date h) {
		if (h == null) {
			h = new Date();
		}
		return HOUR_FORMAT.format(h);
	}

	/**
	 * Formats a Date using "dd/MM/yyyy HH:mm:ss"
	 * 
	 * @param f
	 *            Date to format
	 * 
	 * @return String
	 */
	public static String formateDateTimeWithoutMilliseconds(Date f) {
		if (f == null) {
			f = new Date();
		}
		return DATETIME_FORMAT_WITHOUT_MILLISECONDS.format(f);
	}

	/**
	 * Formats a Date using "dd/MM HH:mm"
	 * 
	 * @param f
	 *            Date to format
	 * 
	 * @return String
	 */
	public static String formateDateTimeWithoutSecondsAndYear(Date f) {
		if (f == null) {
			f = new Date();
		}
		return DATETIME_FORMAT_WITHOUT_SECONDS_AND_YEAR.format(f);
	}

	/**
	 * Compares to Dates using only the hour and minute parts.
	 * 
	 * @param hi
	 *            date
	 * @param hf
	 *            date
	 * 
	 * @return int
	 */
	public static int compareHours(Date hi, Date hf) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(hi);
		Integer _hi = cal.get(Calendar.HOUR_OF_DAY);
		Integer _mi = cal.get(Calendar.MINUTE);

		cal.setTime(hf);
		Integer _hf = cal.get(Calendar.HOUR_OF_DAY);
		Integer _mf = cal.get(Calendar.MINUTE);

		// Are equals by default
		int resul = 0;

		if (_hi == _hf) {
			// Check minuts if hours are equal
			resul = _mi.compareTo(_mf);
		} else {
			// If hours are different only check between them
			resul = _hi.compareTo(_hf);
		}

		return resul;
	}

	/**
	 * Copies the our part of the "hour" parameter to the "date" parameter
	 * 
	 * @param date
	 *            date to
	 * @param hour
	 *            date from
	 * 
	 * @return Date
	 */
	public static Date copyHour(Date date, Date hour) {
		Calendar dateCalendar = Calendar.getInstance();
		Calendar hourCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		hourCalendar.setTime(hour);
		dateCalendar.set(Calendar.HOUR_OF_DAY,
				hourCalendar.get(Calendar.HOUR_OF_DAY));
		dateCalendar.set(Calendar.MINUTE, hourCalendar.get(Calendar.MINUTE));
		dateCalendar.set(Calendar.SECOND, hourCalendar.get(Calendar.SECOND));
		dateCalendar.set(Calendar.MILLISECOND,
				hourCalendar.get(Calendar.MILLISECOND));

		return dateCalendar.getTime();
	}

	/**
	 * True iif from lt to
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static Boolean lowerThanDate(Date from, Date to) {
		return zeroHour(from).compareTo(zeroHour(to)) == -1;
	}

	/**
	 * True iif from le to
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static Boolean lowerOrEqualThanDate(Date from, Date to) {
		int result = zeroHour(from).compareTo(zeroHour(to));
		return result == -1 || result == 0;
	}

	/**
	 * True iif from gt to
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static Boolean greaterThanDate(Date from, Date to) {
		return zeroHour(from).compareTo(zeroHour(to)) == 1;
	}

	/**
	 * True iif from ge to
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static Boolean greaterOrEqualThanDate(Date from, Date to) {
		int result = zeroHour(from).compareTo(zeroHour(to));
		return result == 1 || result == 0;
	}

	/**
	 * True iif from equals to
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static Boolean equalDate(Date from, Date to) {
		return zeroHour(from).compareTo(zeroHour(to)) == 0;
	}

	/**
	 * Truncates (zeroes) the seconds and milliseconds of the Date.
	 * 
	 * @param date
	 *            date to use
	 * 
	 * @return Date
	 */
	public static Date truncateSeconds(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * Truncates (zeroes) the milliseconds of the Date.
	 * 
	 * @param date
	 *            date to use
	 * 
	 * @return Date
	 */
	public static Date truncateMilliseconds(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * Returns the difference in seconds between two dates.
	 * 
	 * @param from
	 *            the date from
	 * @param to
	 *            the date to
	 * @param blankMilliseconds
	 *            if true, milliseconds will be zeroed before comparison
	 * @param enforceOneMillisecond
	 *            if true, this difference will never return 0 (at least 1)
	 * 
	 * @return second difference between dates
	 */
	public static Long secondDifference(Date from, Date to,
			Boolean blankMilliseconds, Boolean enforceOneMillisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		if (blankMilliseconds) {
			calendar.set(Calendar.MILLISECOND, 0);
		}
		Long millisFrom = calendar.getTimeInMillis();
		Long millisTo = copyHour(calendar.getTime(), to).getTime();

		Long millisDifference = ((Double) Math
				.ceil((double) (millisTo - millisFrom) / 1000)).longValue();

		return (millisDifference == 0L && enforceOneMillisecond) ? 1L
				: millisDifference;
	}

	/**
	 * Returns the difference in minutes between two dates.
	 * 
	 * @param from
	 *            the date from
	 * @param to
	 *            the date to
	 * @param blankMilliseconds
	 *            if true, milliseconds will be zeroed before comparisong
	 * @param enforceOneMillisecond
	 *            if true, this difference will never return 0 (at least 1)
	 * 
	 * @return minute difference between dates
	 */
	public static Long minuteDifference(Date from, Date to,
			Boolean blankMilliseconds, Boolean enforceOneMillisecond) {
		return ((Double) Math.ceil((double) secondDifference(from, to,
				blankMilliseconds, enforceOneMillisecond) / 60)).longValue();
	}

	/**
	 * Compares the hours without checking the the other date parts (day, month,
	 * year)
	 * 
	 * @param hourOne
	 *            first hour to compare two
	 * @param hourTwo
	 *            second hour to compare two
	 * 
	 * @return
	 */
	public static int compareFullHours(Date hourOne, Date hourTwo) {
		Date fixedHourTwo = copyHour(hourOne, hourTwo);

		return hourOne.compareTo(fixedHourTwo);
	}

	/**
	 * Adds a minute amount to a date.
	 * 
	 * @param date
	 *            the date to add to
	 * @param minutes
	 *            the minutes to add
	 * @param truncateSeconds
	 *            if true, truncates the seconds of the result
	 * 
	 * @return Date
	 */
	public static Date addMinutes(Date date, Long minutes,
			Boolean truncateSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes.intValue());

		if (truncateSeconds) {
			return truncateSeconds(calendar.getTime());
		} else {
			return calendar.getTime();
		}
	}

	/**
	 * Formats a minutes amount to the format used in the SMSs.
	 * 
	 * Examples For 55 minutes will return 55' For 72 minutes will return 1h12'
	 * For 152 minutes will return 2h32'
	 * 
	 * 
	 * @param minutes
	 * 
	 * @return
	 */
	public static String formatMinutes(Long minutes) {
		String minutesString = "";

		Long hours = minutes / 60L;
		if (hours > 0) {
			minutesString += hours.toString() + "h";
		}

		minutesString += minutes % 60 + "'";

		return minutesString;
	}

	/**
	 * Computes the total milliseconds in an amount of minutes.
	 * 
	 * @param minutes
	 * 
	 * @return
	 */
	public static Long minutesToMillis(Long minutes) {
		return minutes * SECONDS_IN_MINUTE * MILLIS_IN_SECOND;
	}
}
