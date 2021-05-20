package com.testoper.bankservice.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testoper.bankservice.entity.Status;
import com.testoper.bankservice.validator.EnumValidator;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
public class CreateAccountRequest {
	@NotBlank(message = "Account Type should not be null or Empty")
	private String type;
	@NotBlank(message = "Account Status should not be null or Empty")
	@EnumValidator(message = "Invalid Acount Status", enumType = Status.class)
	private String status;
	@NotBlank(message = "Account Holder Name should not be null or Empty")
	private String AccountHolderName;
	@NotBlank(message = "Customer Id should not be null or Empty")
	private String customerId;
	@NotBlank(message = "Account Category Code should not be null or Empty")
	private String categoryCode;
	private boolean isNetBanking;
	@JsonIgnore
	private Long BankId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountHolderName() {
		return AccountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		AccountHolderName = accountHolderName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public boolean isNetBanking() {
		return isNetBanking;
	}

	public void setNetBanking(boolean isNetBanking) {
		this.isNetBanking = isNetBanking;
	}

	public Long getBankId() {
		return BankId;
	}

	public void setBankId(Long bankId) {
		BankId = bankId;
	}

}
