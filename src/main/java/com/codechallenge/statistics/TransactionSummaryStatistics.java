package com.codechallenge.statistics;

import static com.codechallenge.util.DateTimeUtil.getSecondsInCurrentTZ;
import static com.codechallenge.util.DateTimeUtil.isInTimeRange;
import static com.codechallenge.util.DateTimeUtil.isOutOfTimeRange;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codechallenge.dto.Statistic;
import com.codechallenge.dto.Transaction;

/**
 * Calculates statistics for last 60 seconds based on timestamp included in the transaction.
 * Sixty slots are kept in a Map representing each second. Transaction's timestamp is used to figure out the slot.
 * If slot is empty or existing entry is obsolete then current Transaction's Statistic will override. 
 * If any statistics is already available and not obsolete then current Transactio's statistic will be merged with it.
 * This indicates that more than one transaction have same timestamps.
 *
 * Constant memory is used (at max 60 entries) for last 60 seconds which means memory complexity is O(1).
 */
public class TransactionSummaryStatistics {

	private static final Logger logger = LoggerFactory.getLogger(TransactionSummaryStatistics.class);

	private final ConcurrentHashMap<Integer, Statistic> statsForLastSixtySeconds = new ConcurrentHashMap<>();

	/*
	 * Async call will not block the current request.
	 * Collate stats will execute in parallel.
	 */
	public void collateStats(Transaction transaction) {
		CompletableFuture.runAsync(() -> collateNow(transaction));
	}

	private void collateNow(Transaction transaction) {
		if (isInTimeRange(transaction.getTimestamp())) {
			int second = getSecondsInCurrentTZ(transaction.getTimestamp());
			Statistic newStatistic = new Statistic(transaction);
			merge(second, newStatistic);
			logger.info("Statistics Collation Done...");
		}
	}

	/*
	 * Merge the stats with non-obsolete entry else kept the statistics as is.
	 */
	private void merge(int second, Statistic newStatistic) {
		statsForLastSixtySeconds.compute(second, (key, existingValue) -> {
			if (isExistingStatisticInvalid(existingValue)) {
				return newStatistic;
			}
			return Statistic.combineStatsOnly(existingValue, newStatistic);
		});
	}

	private boolean isExistingStatisticInvalid(Statistic value) {
		return value == null || isOutOfTimeRange(value.getTimestamp());
	}

	/*
	 * Generate the statistics by iterating over the constant sized map.
	 */
	public Statistic getStatistics() {
		
		Statistic finalStatistics = new Statistic();
		
		statsForLastSixtySeconds.values().stream()
				.filter(statistic -> isInTimeRange(statistic.getTimestamp()))
				.map(statistic -> statistic)
				.reduce(finalStatistics, Statistic::combineStatsOnly);
		
		logger.info("Statistics Generated...", finalStatistics.toString());

		return finalStatistics;
	}
}
