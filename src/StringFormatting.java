import java.lang.Math;
import java.lang.Integer;

/**
 * A set of methods for formatting Strings
 */
public class StringFormatting {
	/**
	 * Capitalizes the first letter of the input
	 * @param input The String to capitalize
	 * @return The String, with the first letter capitalized
	 */
	public static String capitalizeFirst(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}
	
	/**
	 * Turns an integer into a String, including thousands separators
	 * @param input The integer to convert
	 * @param plus Whether positive integers get a + sign at the beginning
	 * @return A String representing the integer, formatted to look nicer
	 */
	public static String formatInt(int input, boolean plus) {
		String sign;
		
		if (input > 0 && plus) {
			sign = "+";
		} else if (input < 0) {
			sign = "-";
		} else {
			sign = "";
		}
		
		String rawNumString = Integer.toString(Math.abs(input));
		String numString = "";
		
		for (int i = 0; i < rawNumString.length(); i++) {
			if (i % 3 == rawNumString.length() % 3 && i != 0) {
				numString += ",";
			}
			
			numString += rawNumString.charAt(i);
		}
		
		return sign + numString;
	}
	
	/**
	 * Turns an integer into a String, including thousands separators.
	 * <p>
	 * (This is the same as the other formatInt(), but with the "plus" parameter
	 * assumed to be false.)
	 * 
	 * @param input The integer to convert
	 * @return A String representing the integer, formatted to look nicer
	 */
	public static String formatInt(int input) {
		return formatInt(input, false);
	}
}