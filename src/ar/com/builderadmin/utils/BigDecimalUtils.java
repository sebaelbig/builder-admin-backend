package ar.com.builderadmin.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Utils Methods for Big Decimal Operations
 * 
 * @author fgonzalez
 * 
 */
public class BigDecimalUtils {

	/**
	 * Rounds down a BigDecimal to the specified scale.
	 * 
	 * @param number
	 *            the number to round
	 * @param scale
	 *            the scale to use
	 * 
	 * @return
	 */
	public static BigDecimal floor(BigDecimal number, Integer scale) {
		return number.setScale(scale, RoundingMode.FLOOR);
	}

	/**
	 * Rounds up a BigDecimal to the specified scale.
	 * 
	 * @param number
	 *            the number to round
	 * @param scale
	 *            the scale to use
	 * 
	 * @return
	 */
	public static BigDecimal ceiling(BigDecimal number, Integer scale) {
		return number.setScale(scale, RoundingMode.CEILING);
	}

	/**
	 * Rounds a BigDecimal using traditional round up scaling.
	 * 
	 * @param number
	 *            the number to round
	 * @param scale
	 *            the scale to use
	 * 
	 * @return
	 */
	public static BigDecimal halfUp(BigDecimal number, Integer scale) {
		return number.setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * True if a is lower than b
	 * 
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * 
	 * @return
	 */
	public static Boolean lt(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) < 0;
	}

	/**
	 * True if a is lower or equal than b
	 * 
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * 
	 * @return
	 */
	public static Boolean le(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) <= 0;
	}

	/**
	 * True if a is greater than b
	 * 
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * 
	 * @return
	 */
	public static Boolean gt(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) > 0;
	}

	/**
	 * True if a is greater or equal than b
	 * 
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * 
	 * @return
	 */
	public static Boolean ge(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) >= 0;
	}

	/**
	 * True if a is between start and end
	 * 
	 * @param a
	 *            number to compare
	 * @param start
	 *            start of the interval
	 * @param end
	 *            end of the interval
	 * @return
	 */
	public static Boolean between(BigDecimal a, BigDecimal start, BigDecimal end) {
		return le(start, a) && le(a, end);
	}

	/**
	 * True if a is between start and end and it's not equal to neither start or
	 * end
	 * 
	 * @param a
	 *            number to compare
	 * @param start
	 *            start of the interval
	 * @param end
	 *            end of the interval
	 * @return
	 */
	public static Boolean strictBetween(BigDecimal a, BigDecimal start,
			BigDecimal end) {
		return lt(start, a) && lt(a, end);
	}
	
	/**
	 * 
	*/
	public static String parse(BigDecimal a, String pattern) {
		if (pattern==null){
			pattern = "#########.##";
		}
		
		return new DecimalFormat(pattern).format(a);
	}

}
