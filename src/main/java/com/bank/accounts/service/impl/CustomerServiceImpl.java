package com.bank.accounts.service.impl;

import com.bank.accounts.client.CardsFeignClient;
import com.bank.accounts.client.LoansFeignClient;
import com.bank.accounts.dto.AccountsDto;
import com.bank.accounts.dto.CardsDto;
import com.bank.accounts.dto.CustomerDetailsDto;
import com.bank.accounts.dto.LoansDto;
import com.bank.accounts.entity.Accounts;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.mapper.AccountsMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.repository.AccountsRepository;
import com.bank.accounts.repository.CustomerRepository;
import com.bank.accounts.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	private LoansFeignClient loansFeignClient;
	private CardsFeignClient cardsFeignClient;

	public CustomerServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository,
							   LoansFeignClient loansFeignClient, CardsFeignClient cardsFeignClient) {
		this.accountsRepository = accountsRepository;
		this.customerRepository = customerRepository;
		this.cardsFeignClient = cardsFeignClient;
		this.loansFeignClient = loansFeignClient;
	}
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString()));

		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
		customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

		ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
		customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

		ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
		customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

		return customerDetailsDto;
	}
}
