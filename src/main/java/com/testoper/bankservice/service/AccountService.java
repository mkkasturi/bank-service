package com.testoper.bankservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.exception.NotFoundException;
import com.testoper.bankservice.mapper.AccountMapper;
import com.testoper.bankservice.repository.AccountRepository;
import com.testoper.bankservice.request.CreateAccountRequest;
import com.testoper.bankservice.response.CreateAccountResponse;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class AccountService {

	@Autowired
	BankService bankService;

	@Autowired
	AccountMapper accountMapper;

	@Autowired
	AccountRepository accountRepository;

	/**
	 * Creates an Account in the Bank
	 * 
	 * @param createAccountRequest
	 * @return
	 */
	public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
		Bank bank = bankService.getBank(createAccountRequest.getBankId());
		Account account = accountMapper.mapCreateAccountRequestToAccount(createAccountRequest, bank);
		Account createdAccount = accountRepository.save(account);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(createdAccount, CreateAccountResponse.class);
	}

	/**
	 * Gets the Account Details
	 * 
	 * @param id
	 * @param accountId
	 * @return
	 */
	public Account getAccount(Long bankId, Long accountId) {
		Bank bank = bankService.getBank(bankId);
		Optional<Account> account = accountRepository.findByIdAndBank(accountId, bank);
		if (account.isEmpty()) {
			throw new NotFoundException(Constants.ACCOUNT_NOT_FOUND);
		}
		return account.get();
	}

}
