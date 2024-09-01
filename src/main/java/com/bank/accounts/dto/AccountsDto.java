package com.bank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
	@Pattern(regexp = "^$|[0-9]{10}", message = "accountNumber must be 10 digits")
	@NotEmpty(message = "accountNumber can't be null or empty")
	private Long accountNumber;
	@NotEmpty(message = "accountType can't be null or empty")
	private String accountType;
	@NotEmpty(message = "branchAddress can't be null or empty")
	private String branchAddress;
}
