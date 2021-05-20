package com.testoper.bankservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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

import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.request.CreateBankRequest;
import com.testoper.bankservice.response.CreateBankResponse;
import com.testoper.bankservice.service.BankService;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@RestController
@RequestMapping(value = "/bankService/v1")
@Validated
public class BankController {

	@Autowired
	BankService bankService;

	@PostMapping(value = "/banks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateBankResponse> createBank(@Valid @RequestBody CreateBankRequest createBankRequest) {
		CreateBankResponse createBankResponse = bankService.createBank(createBankRequest);
		return ResponseEntity.status(HttpStatus.OK).body(createBankResponse);
	}

	@GetMapping(value = "/banks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bank> getBank(
			@Valid @Positive(message = "Invalid Bank Id") @PathVariable("id") Long id) {
		Bank bank = bankService.getBank(id);
		return ResponseEntity.status(HttpStatus.OK).body(bank);
	}
}
