package com.codechallenge.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.codechallenge.repository.TransactionRepositoryTest;
import com.codechallenge.statistics.TransactionSummaryStatisticsTest;
import com.codechallenge.util.DateTimeUtilTest;

@RunWith(Suite.class)
@SuiteClasses({
TransactionRepositoryTest.class,
TransactionSummaryStatisticsTest.class,
DateTimeUtilTest.class
})
public class AllTests {

}
