package com.bank.accounts.controller;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.AccountsDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.dto.ErrorResponseDto;
import com.bank.accounts.dto.ResponseDto;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
		name = "CRUD REST APIs for Accounts",
		description = "CRUD REST APIs to create/fetch/update/delete Account details"
)
public class AccountsController {

	private AccountsService accountsService;

	// we are doing constructor injection, which is the recommended approach over @Autowired annotation
	public AccountsController(AccountsService accountsService) {
		this.accountsService = accountsService;
	}

	@Operation(
			summary = "Create Account REST API",
			description = "REST API to create new Customer & Account for Bank"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "HTTP Status Created"
			),
			@ApiResponse(
					responseCode = "400",
					description = "HTTP Status Bad Request",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = ErrorResponseDto.class))
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
}
	)
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		accountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(
			summary = "Fetch Account Details REST API",
			description = "REST API to fetch Customer &  Account details based on the mobile number"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
	}
	)
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetailsWithRequestParam(
											@RequestParam("mobileNumber") // we can ignore ("mobileNumber") but the parameter name should match the parameter variable name
											@Pattern(regexp = "^$|[0-9]{10}", message = "mobileNumber must be 10 digits")
											String mobileNumber) {
		CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);

		//return ResponseEntity.status(HttpStatus.OK).body(customerDto);
		return ResponseEntity.ok().body(customerDto);
	}

	@GetMapping("/fetch/{mobileNumber}")
	public ResponseEntity<CustomerDto> fetchAccountDetailsWithPathVariable(
			@PathVariable("mobileNumber") // we can ignore this if the {mobileNumber} is same as the variable name mobileNumber
			@Pattern(regexp = "^$|[0-9]{10}", message = "mobileNumber must be 10 digits")
			String mobileNumber) {
		CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);

		//return ResponseEntity.status(HttpStatus.OK).body(customerDto);
		return ResponseEntity.ok().body(customerDto);
	}

	@Operation(
			summary = "Update Account Details REST API",
			description = "REST API to update Customer &  Account details based on a account number"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
	}
	)
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = accountsService.updateAccount(customerDto);
		if(isUpdated) {
			return ResponseEntity.ok().body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}
		else {
			return ResponseEntity.internalServerError()
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
		}
	}


	@Operation(
			summary = "Delete Account & Customer Details REST API",
			description = "REST API to delete Customer &  Account details based on a mobile number"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
	}
	)
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountsDetailsUsingRequestParam(
										@RequestParam
										@Pattern(regexp = "^$|[0-9]{10}", message = "mobileNumber must be 10 digits")
										String mobileNumber) {
		boolean isDeleted = accountsService.deleteAccount(mobileNumber);
		if(isDeleted) {
			return ResponseEntity.ok()
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}else {
			return ResponseEntity.internalServerError()
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
		}

	}

	@DeleteMapping("/delete/{mobileNumber}")
	public ResponseEntity<ResponseDto> deleteAccountsDetailsUsingPathVariable(
			@PathVariable("mobileNumber")
			@Pattern(regexp = "^$|[0-9]{10}", message = "mobileNumber must be 10 digits")
			String mobileNumber) {
		boolean isDeleted = accountsService.deleteAccount(mobileNumber);
		if(isDeleted) {
			return ResponseEntity.ok()
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}else {
			return ResponseEntity.internalServerError()
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
		}

	}

}
