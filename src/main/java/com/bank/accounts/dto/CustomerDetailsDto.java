package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
		name = "CustomerDetails",
		description = "Schema to hold Customer, Accounts, Cards, Loans info"
)
@Data
public class CustomerDetailsDto {
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
	@NotEmpty(message = "Mobile Number can't be null or empty")
	@Pattern(regexp = "$|[0-9]{10}", message = "mobileNumber must be 10 digits")
	private String mobileNumber;

	@Schema(
			description = "Account Details of the Customer"
	)
	private AccountsDto accountsDto;

	@Schema(
			description = "Card Details of the Customer"
	)
	private CardsDto cardsDto;

	@Schema(
			description = "Loan Details of the Customer"
	)
	private LoansDto loansDto;
}
