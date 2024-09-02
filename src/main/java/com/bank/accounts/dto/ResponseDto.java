package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
		name = "Response",
		description = "Schema to hold successful response information"
)
@Data
@AllArgsConstructor
public class ResponseDto {
	@Schema(
			name = "Status code in the response",
			example = "200"
	)
	private String statusCode;

	@Schema(
			name = "Status message in the response",
			example = "Request Processed Successfully"
	)
	private String statusMsg;
}
