package com.codechallenge.transaction.dto;

import java.util.DoubleSummaryStatistics;

/*
 * DTO to capture the statistic of any transaction.
 */
public class Statistic {

	private long timestamp;
	private DoubleSummaryStatistics stats;

	public Statistic() {
		super();
		this.timestamp = System.currentTimeMillis();
		this.stats = new DoubleSummaryStatistics();
	}

	public Statistic(Transaction transaction) {
		this();
		this.timestamp = transaction.getTimestamp();
		this.stats.accept(transaction.getAmount());
	}

	public static Statistic combineStatsOnly(Statistic first, Statistic second) {
		first.stats.combine(second.getStats());
		return first;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public DoubleSummaryStatistics getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return "Statistic [timestamp=" + timestamp + ", stats=" + stats + "]";
	}

}
