package com.codechallenge.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {

	private static final int SECONDS_STATS = 60;

	public static final ZoneId zoneId = ZoneId.systemDefault();
	
	/*
	 * Converts the timestamp into current time zone and provide the second's part.
	 */
	public static int getSecondsInCurrentTZ(long timestamp) {
		Instant epochMilli = Instant.ofEpochMilli(timestamp);
		LocalDateTime instant = LocalDateTime.ofInstant(epochMilli, zoneId);
		return instant.getSecond();
	}

	/*
	 * Checks if any timestamp is in last 60 seconds.
	 */
	public static boolean isInTimeRange(long timestamp) {
		long currentTimeMillis = System.currentTimeMillis();
		return (currentTimeMillis - timestamp) / 1000 < SECONDS_STATS;
	}
	
	public static boolean isOutOfTimeRange(long timestamp) {
		return !isInTimeRange(timestamp);
	}
}
