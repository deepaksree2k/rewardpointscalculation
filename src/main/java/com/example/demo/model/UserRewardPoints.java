package com.example.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRewardPoints {
	String userId;
	
	String totalRewardPoints;
	
	List<RewardPoints> rewardpoints;

}
