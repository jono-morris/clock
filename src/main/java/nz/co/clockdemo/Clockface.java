package nz.co.clockdemo;

import java.util.regex.Pattern;

public class Clockface {

	/** Regex pattern for testing whether a 4 character string is a valid 24 hour time. */
	private static final Pattern TIME_PATTERN = Pattern.compile("([01]?[0-9]|2[0-3])[0-5][0-9]");
	
	/**
	 * Calculates the angle in degrees between the hour hand and the minute hand measured 
	 * clockwise from the hour-hand around to the minute hand.
	 * 
	 * @param time 24 hour time in hhmm format; required
	 * @return the calculated angle
	 */
	public double clocke(String time) {

		if (null == time) {
			throw new IllegalArgumentException("time parameter must not be null");
		}
		
		if(time.length() != 4) {
			throw new IllegalArgumentException("invalid time string, should be formatted as <hhmm>");
		}
		
		if (!TIME_PATTERN.matcher(time).matches()) {
			throw new IllegalArgumentException("invalid time string, should be a valid 24 hour time");
		}
		
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(2));
		
		double minuteAngle = minute / 60.0 * 360.0;
		// Unless the minute is zero, the hour hand will not sit exactly on an hour.
		double hourAngle = (hour / 12.0 * 360.0) % 360.0  + minute * (360.0 / (12.0 * 60.0));
		
		return hourAngle > minuteAngle ? 360.0 - (hourAngle - minuteAngle) : minuteAngle - hourAngle;
	}
}
