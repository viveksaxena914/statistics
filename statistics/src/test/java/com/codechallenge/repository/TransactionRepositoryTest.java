package com.codechallenge.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codechallenge.transaction.dto.Transaction;
import com.codechallenge.util.DataUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackageClasses = { TransactionRepository.class, })
@EntityScan("com.codechallenge.transaction.dto")
public class TransactionRepositoryTest {

	@Autowired
	private TransactionRepository transactionRepository;

	@DirtiesContext
	@Test
	public void test_Save_Simple_Transaction_And_Check() {
		Transaction transaction = DataUtil.getOneTransaction();
		transactionRepository.save(transaction);

		Optional<Transaction> transactionFromDB = transactionRepository.findById(transaction.getId());
		assertEquals(transaction, transactionFromDB.get());
	}

	@DirtiesContext
	@Test
	public void test_Save_All_Transactions_And_Fetch() {
		List<Transaction> transactionList = DataUtil.getTransactions();

		transactionRepository.saveAll(transactionList);

		Iterable<Transaction> all = transactionRepository.findAll();
		all.forEach(t -> {
			if (!transactionList.contains(t)) {
				throw new RuntimeException("Missing Transaction : " + t);
			}
		});
	}

}
