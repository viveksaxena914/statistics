package com.codechallenge.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTimeUtilTest {

	@Test
	public void testGetTimeInCurrentTZ() {
		int expectedSeconds = 44;
		long timestamp = 1478192204000L;
		int inCurrentTZ = DateTimeUtil.getSecondsInCurrentTZ(timestamp);
		assertEquals(expectedSeconds, inCurrentTZ);
	}

}
