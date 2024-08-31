package com.bank.accounts.exception;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

//@ControllerAdvice  -- this is applicable for all types of Controllers which returns REST SOAP or a HTML View
@RestControllerAdvice   // -- this is only applicable for REST Controllers ->@RestControllerAdvice = @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	//@ResponseBody -- this is redundant as we are using @RestControllerAdvice, the response will be considered as a direct response, if we are using only @ControllerAdvice
	// then we must use this otherwise whatever we are returning from the below method, it will be treated as a view and will be sent to the view resolver
	//@ResponseBody bypasses the view resolution mechanism
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
																				 WebRequest webRequest){
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST,
				exception.getMessage(),
				LocalDateTime.now()
		);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.NOT_FOUND,
				exception.getMessage(),
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}
}
