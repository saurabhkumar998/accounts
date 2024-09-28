package com.bank.accounts.controller;

import com.bank.accounts.dto.CustomerDetailsDto;
import com.bank.accounts.dto.ErrorResponseDto;
import com.bank.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
		name = "CRUD REST APIs for Customers of the Bank",
		description = "CRUD REST APIs to fetch Customer details"
)
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	@Operation(
			summary = "Fetch Customer Details REST API",
			description = "REST API to fetch Customer details based on the mobile number"
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
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam("mobileNumber") // we can ignore ("mobileNumber") but the parameter name should match the parameter variable name
																   @Pattern(regexp = "^$|[0-9]{10}", message = "mobileNumber must be 10 digits")
																   String mobileNumber){
		CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber);

		return ResponseEntity.status(HttpStatus.OK)
				.body(customerDetailsDto);
	}

}
