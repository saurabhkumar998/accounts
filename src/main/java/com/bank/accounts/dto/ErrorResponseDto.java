package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
		name = "Error Response",
		description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
	@Schema(
			description = "API path invoked by the client"
	)
	private String apiPath;

	@Schema(
			description = "Error Code representing the type of error occurred"
	)
	private HttpStatus errorCode;

	@Schema(
			description = "Error Message describing the type of error occurred"
	)
	private String errorMessage;

	@Schema(
			description = "Error Time when the error occurred"
	)
	private LocalDateTime errorTime;

}
