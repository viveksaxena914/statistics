package com.codechallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codechallenge.statistics.TransactionSummaryStatistics;
import com.codechallenge.dto.Statistic;

/*
 * Service responsible for handling Statistics related requests.
 */
@Service
public class StatisticService {

	private TransactionSummaryStatistics transactionSummaryStatistics;

	@Autowired
	public StatisticService(TransactionSummaryStatistics stats) {
		super();
		this.transactionSummaryStatistics = stats;
	}

	public Statistic getStatistics() {
		return transactionSummaryStatistics.getStatistics();
	}

}
