package com.testoper.bankservice.mapper;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.entity.Status;
import com.testoper.bankservice.request.CreateAccountRequest;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class AccountMapper {

	public Account mapCreateAccountRequestToAccount(CreateAccountRequest createAccountRequest, Bank bank) {
		return new Account(generateAccountNumber(bank.getCode(), createAccountRequest.getCustomerId()),
				createAccountRequest.getType(), Status.valueOf(createAccountRequest.getStatus()),
				createAccountRequest.getAccountHolderName(), createAccountRequest.getCustomerId(),
				createAccountRequest.getCategoryCode(), 0.0, createAccountRequest.isNetBanking(), bank);
	}

	private static String generateAccountNumber(String bankCode, String customerId) {
		return String.valueOf(bankCode.hashCode()) + String.valueOf(customerId.hashCode())
				+ String.valueOf(Instant.now().getEpochSecond());
	}

}
