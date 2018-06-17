package com.codechallenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.codechallenge.transaction.dto.Transaction;

/*
 * This repository is based on Spring Data Rest and exposes a lot of functionalities
 * automatically.
 */
@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
