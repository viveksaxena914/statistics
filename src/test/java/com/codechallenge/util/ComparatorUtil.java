package com.codechallenge.util;

import java.util.DoubleSummaryStatistics;

public class ComparatorUtil {
	
	public static int compare(DoubleSummaryStatistics first, DoubleSummaryStatistics second) {
		
		int retVal = Long.valueOf(first.getCount()).compareTo(second.getCount());
		
		if(retVal == 0) {
			retVal = Double.compare(first.getAverage(), second.getAverage());
			if(retVal == 0) {
				retVal = Double.compare(first.getMax(), second.getMax());
				if(retVal == 0) {
					retVal = Double.compare(first.getMin(), second.getMin());
					if(retVal == 0) {
						retVal = Double.compare(first.getSum(), second.getSum());
					}
				}
			}
		}
		return retVal;
	}

}
