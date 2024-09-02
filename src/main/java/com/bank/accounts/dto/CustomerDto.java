package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(
		name = "Customer",
		description = "Schema to hold Customer and Account info"
)
@Data
public class CustomerDto {
	@Schema(
			description = "Name of the Customer",
			example = "John Doe"
	)
	@NotEmpty(message = "name can't be null or empty")
	//@NotNull(message = "name can't be null") -- this is redundant as @NotEmpty performs null check as well
	@Size(min = 5, max = 30, message = "length of name should be between 5 to 30")
	private String name;

	@Schema(
			description = "Email of the Customer",
			example = "johndoe@gmail.com"
	)
	@NotEmpty(message = "email can't be null or empty")
	@Email(message = "email should be a valid value")
	private String email;

	@Schema(
			description = "Mobile Number of the Customer",
			example = "9999999999"
	)
	@NotEmpty
	@Pattern(regexp = "$|[0-9]{10}", message = "mobileNumber must be 10 digits")
	private String mobileNumber;

	@Schema(
			description = "Account Details of the Customer"
	)
	private AccountsDto accountsDto;
}
