package com.codechallenge.statistics;

import static com.codechallenge.util.ComparatorUtil.compare;
import static org.junit.Assert.assertEquals;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codechallenge.transaction.dto.Statistic;
import com.codechallenge.transaction.dto.Transaction;
import com.codechallenge.util.DataUtil;

public class TransactionSummaryStatisticsTest {

	private TransactionSummaryStatistics transactionSummaryStatistics;
	
	@Before
	public void eachTest() {
		transactionSummaryStatistics = new TransactionSummaryStatistics();
	}
	
	@Test
	public void test_No_Statistics_Available() {
		Statistic expected = new Statistic();
		Statistic statistic = transactionSummaryStatistics.getStatistics();
		compareStatsOnly(expected, statistic);
	}
	
	@Test
	public void test_For_One_Transaction() throws InterruptedException {
		Transaction transaction = DataUtil.getOneTransaction();
		Statistic expected = new Statistic(transaction);
		transactionSummaryStatistics.collateStats(transaction);
		Thread.sleep(500);
		Statistic statistic = transactionSummaryStatistics.getStatistics();
		compareStatsOnly(expected, statistic);
	}
	
	@Test
	public void test_For_Multiple_Transaction() throws InterruptedException {
		DoubleSummaryStatistics expectedStatistics = new DoubleSummaryStatistics();
		List<Transaction> transactions = DataUtil.getTransactions();
		transactions.stream().forEach(t -> {
			transactionSummaryStatistics.collateStats(t);
			expectedStatistics.accept(t.getAmount());
		});
		
		Thread.sleep(500);
		Statistic statistic = transactionSummaryStatistics.getStatistics();
		assertEquals(0, compare(expectedStatistics, statistic.getStats()));
	}
	
	@Test
	public void test_For_Multiple_Transaction_With_Eviction() throws InterruptedException {
		List<Transaction> transactions = DataUtil.getTransactionsWithTimeVariance();
		DoubleSummaryStatistics expectedStatistics = new DoubleSummaryStatistics();
		expectedStatistics.accept(transactions.get(transactions.size() - 1).getAmount());
		
		transactions.stream().forEach(t -> {
			transactionSummaryStatistics.collateStats(t);
		});
		
		Thread.sleep(1000);
		Statistic statistic = transactionSummaryStatistics.getStatistics();
		System.out.println(statistic.getStats());
		assertEquals(0, compare(expectedStatistics, statistic.getStats()));
	}

	public void compareStatsOnly(Statistic first, Statistic second) {
		assertEquals(0, compare(first.getStats(), second.getStats()));
	}

}
