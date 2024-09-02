package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
		name = "Accounts",
		description = "Schema to hold the Account info"
)
@Data
public class AccountsDto {
	@Schema(
			description = "Account Number of the Customer",
			example = "3243409111"
	)
	@Pattern(regexp = "^$|[0-9]{10}", message = "accountNumber must be 10 digits")
	@NotEmpty(message = "accountNumber can't be null or empty")
	private Long accountNumber;

	@Schema(
			description = "Account Type of the Customer",
			example = "Savings"
	)
	@NotEmpty(message = "accountType can't be null or empty")
	private String accountType;

	@Schema(
			description = "Branch Address of the Customer",
			example = "123, Main Street, California"
	)
	@NotEmpty(message = "branchAddress can't be null or empty")
	private String branchAddress;
}
