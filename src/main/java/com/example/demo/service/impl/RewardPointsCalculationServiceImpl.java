package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RewardPoints;
import com.example.demo.model.Transaction;
import com.example.demo.model.UserDetails;
import com.example.demo.model.UserRewardPoints;
import com.example.demo.repository.TransactionRespository;
import com.example.demo.service.RewardPointsCalculationService;

/**
 * 
 * Service implementation to calculate total reward points for given user
 * 
 *
 */

@Service
public class RewardPointsCalculationServiceImpl implements RewardPointsCalculationService {

	private static final int MIN_AMOUNT_ONE_REWARD = 50;
	private static final int MIN_AMOUNT_TWO_REWARD = 100;

	@Autowired

	TransactionRespository transactionRepository;

	/**
	 * calculate reward points for each user
	 */

	@Override
	public UserDetails calculateRewardPoints() {

		UserDetails userDetails = new UserDetails();
		List<UserRewardPoints> userRewardPointsList = new ArrayList<>();

		List<Transaction> transaction = (List<Transaction>) transactionRepository.findAll();
		Map<String, Map<String, List<Transaction>>> transactionsUserMap = transformTransactions(transaction);

		transactionsUserMap.forEach((k, v) -> {
			Map<String, Long> rewardpointsMonthMap = calculateRewardForEachMonth(v);
			userRewardPointsList.add(transformRewardPoints(rewardpointsMonthMap, k));
		});
		userDetails.setUserDetails(userRewardPointsList);

		return userDetails;
	}

	/**
	 * transforms the reward details to {@UserRewardPoints}
	 * 
	 * @param rewardpointsMonthMap
	 * @param userId
	 * @return
	 */

	private UserRewardPoints transformRewardPoints(final Map<String, Long> rewardpointsMonthMap, final String userId) {
		List<RewardPoints> rewardpointsList = new ArrayList<>();
		UserRewardPoints userRewardPoints = new UserRewardPoints();
		rewardpointsMonthMap.forEach((k, v) -> {
			RewardPoints rewardpoints = new RewardPoints();
			rewardpoints.setMonth(k);
			rewardpoints.setPoints(v.toString());
			rewardpointsList.add(rewardpoints);
		});

		Long totalRewardPoints = rewardpointsList.stream().mapToLong(o -> Long.parseLong(o.getPoints())).sum();

		userRewardPoints.setUserId(userId);
		userRewardPoints.setRewardpoints(rewardpointsList);
		userRewardPoints.setTotalRewardPoints(totalRewardPoints.toString());
		return userRewardPoints;

	}
	
	
	
	
	/**
	 * Converts  transactions to user per month
	 * @param transactionsList
	 * @return
	 */
	
	private Map<String, Map<String, List<Transaction>>>transformTransactions(final List<Transaction> transactionsList) {
		
		Map<String, List<Transaction>> userTransactionsMap=	transactionsList
	    .stream()
	    .collect(Collectors.groupingBy(transaction ->transaction.getUserId()));
		
		 return userTransactionsMap.entrySet().stream().collect(
				Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().stream(). collect(Collectors.groupingBy(transaction ->transaction.getMonth()))));
		
	}

	/**
	 * Calculates reward points for each month
	 * 
	 * @param transactionsOfMonths
	 * @return reward points for each month
	 */

	private Map<String, Long> calculateRewardForEachMonth(final Map<String, List<Transaction>> transactionsOfMonths) {
		Map<String, Long> hashMap = transactionsOfMonths.entrySet().stream().collect(
				Collectors.toMap(entry -> entry.getKey(), entry -> calculateTotalRewardPoints(entry.getValue())));
		return hashMap;
	}
	

	/**
	 * Calculates reward points for the transaction
	 * 
	 * @param transaction
	 *            
	 * @return reward points for that transaction
	 */

	private Long calculateRewardPoints(final Transaction transaction) {
		final BigDecimal transactionAmt=transaction.getTransactionAmount();
		if (transactionAmt.compareTo(new BigDecimal(MIN_AMOUNT_ONE_REWARD)) >= 0
				&& transactionAmt.compareTo(new BigDecimal(MIN_AMOUNT_TWO_REWARD)) <= 0) {
			return transactionAmt.subtract(new BigDecimal(MIN_AMOUNT_ONE_REWARD)).longValue();
		} else if (transactionAmt.compareTo(new BigDecimal(MIN_AMOUNT_TWO_REWARD)) > 0) {
			return (transactionAmt.subtract(new BigDecimal(MIN_AMOUNT_TWO_REWARD)).longValue() * 2
					+ MIN_AMOUNT_ONE_REWARD);
		} else {
			return Long.valueOf(0);
		}
	}

	/**
	 * takes transactions per month and returns total reward points for that
	 * month
	 * 
	 * @param transactionamountList
	 *            for each each month
	 * @return total reward points
	 */

	private long calculateTotalRewardPoints(final List<Transaction> transactionList) {
		return transactionList.stream().mapToLong((transaction) -> {
			return calculateRewardPoints(transaction);
		}).sum();
	}

}
