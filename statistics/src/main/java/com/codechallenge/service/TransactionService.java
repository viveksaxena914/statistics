package com.codechallenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codechallenge.exception.OutOfTimeRange;
import com.codechallenge.repository.TransactionRepository;
import com.codechallenge.statistics.TransactionSummaryStatistics;
import com.codechallenge.transaction.dto.Transaction;
import static com.codechallenge.util.DateTimeUtil.isOutOfTimeRange;

/*
 * TransactionService service is helpful for handling transaction related functionalities.
 */
@Service
public class TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	private TransactionRepository transactionRepository;

	private TransactionSummaryStatistics transactionSummaryStatistics;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository,
			TransactionSummaryStatistics transactionSummaryStatistics) {
		super();
		this.transactionRepository = transactionRepository;
		this.transactionSummaryStatistics = transactionSummaryStatistics;
	}

	/*
	 * Save the transaction to DB, validate the transaction if it can be included to 
	 * for transaction statistics.
	 */
	public void save(Transaction transaction) {

		this.transactionRepository.save(transaction);
		logger.info("Transaction Saved : " + transaction);

		validateForStatistics(transaction);
		transactionSummaryStatistics.collateStats(transaction);
	}
	
	/*
	 *  OutOfTimeRange indicates that current transaction is older than expected
	 *  and cannot be included statistics generation.
	 */
	private void validateForStatistics(Transaction transaction) {
		if (isOutOfTimeRange(transaction.getTimestamp())) {
			throw new OutOfTimeRange();
		}
	}

	public Iterable<Transaction> findAll() {
		return this.transactionRepository.findAll();
	}
}
