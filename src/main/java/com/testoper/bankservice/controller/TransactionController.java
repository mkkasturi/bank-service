package com.testoper.bankservice.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.request.WithdrawlRequest;
import com.testoper.bankservice.response.WithdrawlResponse;
import com.testoper.bankservice.service.TransactionService;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@RestController
@RequestMapping(value = "/transactionService/v1")
@Validated
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	@PostMapping(value = "/withdrawl", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WithdrawlResponse> createBank(@Valid @RequestBody WithdrawlRequest withdrawlRequest) {
		Account account = transactionService.withdrawl(withdrawlRequest);
		ModelMapper modelMapper = new ModelMapper();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(account, WithdrawlResponse.class));
	}
}
