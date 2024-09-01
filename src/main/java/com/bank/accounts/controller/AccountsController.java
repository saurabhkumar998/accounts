package com.bank.accounts.controller;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.AccountsDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.dto.ErrorResponseDto;
import com.bank.accounts.dto.ResponseDto;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.service.AccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {

	private AccountsService accountsService;

	// we are doing constructor injection, which is the recommended approach over @Autowired annotation
	public AccountsController(AccountsService accountsService) {
		this.accountsService = accountsService;
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		accountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
											@RequestParam
											@Pattern(regexp = "^$|[0-9]{10}", message = "mobileNumber must be 10 digits")
											String mobileNumber) {
		CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);

		//return ResponseEntity.status(HttpStatus.OK).body(customerDto);
		return ResponseEntity.ok().body(customerDto);
	}

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

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountsDetails(
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

}
