package com.testoper.bankservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
public class CreateBankRequest {
	@NotBlank(message = "Bank Name should not be null or Empty")
	private String name;
	@NotBlank(message = "Bank Code should not be null or Empty")
	private String code;
	@NotBlank(message = "Bank Address should not be null or Empty")
	private String address;
	private String phoneNumber;
	@Email
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
