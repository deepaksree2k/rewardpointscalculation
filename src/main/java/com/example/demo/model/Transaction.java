package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Entity
public class Transaction {
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
	private String userId;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private BigDecimal transactionAmount;
    
    Transaction(){
    	
    }

}
