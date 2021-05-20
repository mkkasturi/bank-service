package com.testoper.bankservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.exception.NotFoundException;
import com.testoper.bankservice.repository.BankRepository;
import com.testoper.bankservice.request.CreateBankRequest;
import com.testoper.bankservice.response.CreateBankResponse;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class BankService {
	
	@Autowired
	BankRepository bankRepository;


	/**
	 * 
	 * Creates Bank and saves into the Map
	 * 
	 * @param createBankRequest
	 * @return
	 */
	public CreateBankResponse createBank(CreateBankRequest createBankRequest) {
		ModelMapper modelMapper = new ModelMapper();
		Bank bank = modelMapper.map(createBankRequest, Bank.class);
		Bank createdBank = bankRepository.save(bank);
		return modelMapper.map(createdBank, CreateBankResponse.class);
	}

	/**
	 * 
	 * Gets Bank based on the Bank Id
	 * 
	 * @param id
	 * @return
	 */
	public Bank getBank(Long id) {
		Optional<Bank> bank = bankRepository.findById(id);
		if (bank.isEmpty()) {
			throw new NotFoundException(Constants.BANK_NOT_FOUND);
		}
		return bank.get();
	}

}
