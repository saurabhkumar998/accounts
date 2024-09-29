package com.bank.accounts.service;

import com.bank.accounts.dto.CustomerDetailsDto;

public interface CustomerService {
	CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber);
}
