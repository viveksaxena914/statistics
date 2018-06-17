package com.codechallenge.util;

import java.util.Arrays;
import java.util.List;

import com.codechallenge.dto.Transaction;

public class DataUtil {

	public static Transaction getOneTransaction() {
		double amount = 10.0;
		long timestamp = System.currentTimeMillis();
		return new Transaction(amount, timestamp);
	}

	public static List<Transaction> getTransactions() {
		long timestamp = System.currentTimeMillis();
		Transaction transaction1 = new Transaction(10.0, timestamp);
		Transaction transaction2 = new Transaction(30.5, timestamp);
		Transaction transaction3 = new Transaction(19.5, timestamp + 10000);
		return Arrays.asList(transaction1, transaction2, transaction3);
	}
	
	public static List<Transaction> getTransactionsWithTimeVariance() {
		long timestamp = System.currentTimeMillis();
		Transaction transaction1 = new Transaction(10.0, timestamp - 59000 );
		Transaction transaction2 = new Transaction(30.5, timestamp - 59000 );
		Transaction transaction3 = new Transaction(19.5, timestamp - 55000 );
		return Arrays.asList(transaction1, transaction2, transaction3);
	}
}
