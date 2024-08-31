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
public class Customer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "generic", strategy = "generic")
	@Column(name = "customer_id")
	private Long customerId;
	private String name;
	private String email;
	@Column(name = "mobile_number")
	private String mobileNumber;
}
