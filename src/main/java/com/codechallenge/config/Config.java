package com.codechallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codechallenge.statistics.TransactionSummaryStatistics;

@Configuration
public class Config {

	@Bean
	public TransactionSummaryStatistics captureStats() {
		return new TransactionSummaryStatistics();
	}
}
