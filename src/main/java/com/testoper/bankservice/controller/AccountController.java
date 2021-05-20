package com.testoper.bankservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.request.CreateAccountRequest;
import com.testoper.bankservice.response.AccountResponse;
import com.testoper.bankservice.response.CreateAccountResponse;
import com.testoper.bankservice.service.AccountService;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@RestController
@RequestMapping(value = "/accountService/v1")
@Validated
public class AccountController {
	@Autowired
	AccountService accountService;

	@PostMapping(value = "/banks/{bankId}/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateAccountResponse> createAccount(
			@Valid @RequestBody CreateAccountRequest createAccountRequest) {
		CreateAccountResponse createAccountResponse = accountService.createAccount(createAccountRequest);
		return ResponseEntity.status(HttpStatus.OK).body(createAccountResponse);
	}

	@GetMapping(value = "banks/{bankId}/accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountResponse> getAccount(
			@Valid @Positive(message = "Invalid Bank Id") @PathVariable("bankId") Long bankId,
			@Valid @Positive(message = "Invalid Account Id") @PathVariable("accountId") Long accountId) {
		Account account = accountService.getAccount(bankId, accountId);
		ModelMapper modelMapper = new ModelMapper();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(account, AccountResponse.class));
	}
}
