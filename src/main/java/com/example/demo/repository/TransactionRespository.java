package com.example.demo.repository;
import java.util.List;

import org.springframework.data.repository.*;

import com.example.demo.model.Transaction;

/**
 * Interface to get transaction details from Database
 * 
 *
 */
public interface TransactionRespository extends CrudRepository<Transaction,Long> {
	
	/**
	 * Returns transactions  per user
	 * @param userId
	 * @return transaction List per user
	 */
	List<Transaction> findByUserId(String userId);
	

}
