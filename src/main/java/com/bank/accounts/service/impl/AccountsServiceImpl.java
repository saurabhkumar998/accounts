package com.bank.accounts.service.impl;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.entity.Accounts;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.repository.AccountsRepository;
import com.bank.accounts.repository.CustomerRepository;
import com.bank.accounts.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements AccountsService {
	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	public AccountsServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
		this.accountsRepository = accountsRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already present with given Mobile Number : " + customerDto.getMobileNumber());
		}
		customer.setCreatedBy("Anonymous");
		customer.setCreatedAt(LocalDateTime.now());

		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccount(savedCustomer));
	}

	/**
	 *
	 * @param customer - Customer object
	 * @return the new account object
	 */
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccountNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		newAccount.setCreatedBy("Anonymous");
		newAccount.setCreatedAt(LocalDateTime.now());
		return newAccount;
	}

}
