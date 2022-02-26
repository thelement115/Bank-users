package com.vobi.bank.dto;

import java.util.ArrayList;
import java.util.List;


import com.vobi.bank.domain.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String userEmail;

	
	private Integer ustyId;

	
	private String enable;
	

	private String name;
	
	private String token;

	
	private List<Transaction> transactions = new ArrayList<>();
	
}
