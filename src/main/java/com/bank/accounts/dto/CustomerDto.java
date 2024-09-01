package com.bank.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {
	@NotEmpty(message = "name can't be null or empty")
	//@NotNull(message = "name can't be null") -- this is redundant as @NotEmpty performs null check as well
	@Size(min = 5, max = 30, message = "length of name should be between 5 to 30")
	private String name;
	@NotEmpty(message = "email can't be null or empty")
	@Email(message = "email should be a valid value")
	private String email;
	@Pattern(regexp = "$|[0-9]{10}", message = "mobileNumber must be 10 digits")
	private String mobileNumber;
	private AccountsDto accountsDto;
}
