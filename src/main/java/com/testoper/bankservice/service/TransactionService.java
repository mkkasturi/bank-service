package com.testoper.bankservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Status;
import com.testoper.bankservice.exception.InactiveAccountException;
import com.testoper.bankservice.exception.InsufficientBalanceException;
import com.testoper.bankservice.repository.AccountRepository;
import com.testoper.bankservice.request.WithdrawlRequest;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class TransactionService {
	@Autowired
	BankService bankService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	public Account withdrawl(WithdrawlRequest withdrawlRequest) {
		Account account = accountService.getAccount(withdrawlRequest.getBankId(), withdrawlRequest.getAccountId());
		if (account.getStatus() == Status.INACTIVE) {
			throw new InactiveAccountException(Constants.INACTIVE_BANK_ACCOUNT);
		}
		Double currentBalance = account.getBalance();
		if (withdrawlRequest.getTransactionAmount() > currentBalance) {
			throw new InsufficientBalanceException(Constants.INSUFFICIENT_ACCOUNT_BALANCE);
		}
		account.setBalance(currentBalance - withdrawlRequest.getTransactionAmount());
		return accountRepository.save(account);
	}

}
