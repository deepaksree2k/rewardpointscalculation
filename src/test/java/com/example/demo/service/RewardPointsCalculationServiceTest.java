package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.demo.model.Transaction;
import com.example.demo.model.UserDetails;
import com.example.demo.model.UserRewardPoints;
import com.example.demo.repository.TransactionRespository;
import com.example.demo.service.impl.RewardPointsCalculationServiceImpl;

@SpringBootTest

public class RewardPointsCalculationServiceTest {

	@Mock
	
    private TransactionRespository transactionRepository;

    @InjectMocks // auto inject helloRepository
    private RewardPointsCalculationService rewardsCalculationService = new RewardPointsCalculationServiceImpl();
    
    String userId="abc";

    @BeforeEach
    void setMockOutput() {
    	
       when(transactionRepository.findAll()).thenReturn(getListOftransactions());
    }

  
    @Test
    void testGet() {
    	UserDetails userdetails= rewardsCalculationService.calculateRewardPoints();
    	Assert.notNull(userdetails, "userDetails is null");
    	
    	List<UserRewardPoints> userRewardPointslist=userdetails.getUserDetails();
    	Assert.notEmpty(userRewardPointslist, "reward is empty");
    	
        assertEquals("990", userRewardPointslist.get(0).getTotalRewardPoints());
        assertEquals("660", userRewardPointslist.get(0).getRewardpoints().get(0).getPoints());
    }
    
    private List<Transaction > getListOftransactions(){
    	List<Transaction> transactionList= new ArrayList<> ();
    	Transaction tranctionMonth1=new Transaction(1L,"abc","1",new BigDecimal(200));
    	Transaction tranctionMonth2=new Transaction(2L,"abc","1",new BigDecimal(80));
    	Transaction tranctionMonth3=new Transaction(3L,"abc","1",new BigDecimal(40));
    	Transaction tranctionMonth4=new Transaction(4L,"abc","1",new BigDecimal(100));
    	Transaction tranctionMonth5=new Transaction(5L,"abc","2",new BigDecimal(200));
    	Transaction tranctionMonth6=new Transaction(6L,"abc","2",new BigDecimal(80));
    	Transaction tranctionMonth7=new Transaction(7L,"abc","2",new BigDecimal(40));
    	Transaction tranctionMonth8=new Transaction(8L,"abc","2",new BigDecimal(100));
    	Transaction tranctionMonth9=new Transaction(9L,"abc","1",new BigDecimal(200));
    	Transaction tranctionMonth10=new Transaction(10L,"abc","1",new BigDecimal(80));
    	Transaction tranctionMonth11=new Transaction(11L,"abc","1",new BigDecimal(40));
    	Transaction tranctionMonth12=new Transaction(12L,"abc","1",new BigDecimal(100));
    	transactionList.add(tranctionMonth1);
    	transactionList.add(tranctionMonth2);
    	transactionList.add(tranctionMonth3);
    	transactionList.add(tranctionMonth4);
    	transactionList.add(tranctionMonth5);
    	transactionList.add(tranctionMonth6);
    	transactionList.add(tranctionMonth7);
    	transactionList.add(tranctionMonth8);
    	transactionList.add(tranctionMonth9);
    	transactionList.add(tranctionMonth10);
    	transactionList.add(tranctionMonth11);
    	transactionList.add(tranctionMonth12);
    	return transactionList;
    	

}

}
