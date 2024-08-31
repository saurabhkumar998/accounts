package com.bank.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "customer")  -- this is redundant as it will create the table name with the class's name in lowercase i.e. customer
public class Accounts extends BaseEntity {

	@Column(name = "customer_id")
	private Long customerId;

	@Id
	@Column(name = "account_number")
	private Long accountNumber;

	private String accountType;

	private String branchAddress;
}
