package com.lognsys.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import com.google.gson.Gson;

public class CommonUtilities {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Convert string arr to int arr
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] parseIntArray(String[] arr) {
		return Stream.of(arr).mapToInt(Integer::parseInt).toArray();
	}

	/**
	 * Map to json
	 */
	public static <T> String convertToJSON(List<T> list) {
		Gson gson = new Gson();
		String str = gson.toJson(list);
		return str;
	}

	/**
	 * Splits the String by Delimiter.
	 * 
	 * Assumption: String has 2 values
	 * 
	 * Description: if String > 2 values. Concatenate 1 to n-1
	 * 
	 * Result String[] of size 2
	 * 
	 * Test Cases: 1) "Priyank Doshi" 2) "Priyank " 3) "" 4) null
	 * 
	 * @param str
	 * @param delimeter
	 * @return array with 1, 2 values or null
	 */
	public static String[] splitByDelemeter(String str, String delimeter) {

		if (str != null) {
			String[] vals = str.trim().split(delimeter);

			if (vals.length > 2) {
				String[] fixVal = new String[2];
				StringBuilder builder = new StringBuilder();

				int counter = 0;
				for (String val : vals) {

					if (vals.length - 1 == counter) {
						fixVal[0] = builder.toString().trim();
						fixVal[1] = val;
						return fixVal;
					} else {
						builder.append(val);
						builder.append(delimeter);
					}
					counter++;
				}
			}

			return vals;
		}
		return null;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {

		boolean isValid = false;

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			isValid = true;
		}

		return isValid;
	}

}
