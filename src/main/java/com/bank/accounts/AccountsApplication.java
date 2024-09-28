package com.bank.accounts;

import com.bank.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*  -- the below config is only needed when your AccountsApplication class is inside some sub folders(not in main package), means they are not present inside the src/main/java/com/bank/accounts path
@ComponentScans({ @ComponentScan("com.bank.accounts.controller") })
@EnableJpaRepositories("com.bank.accounts.repository")
@EntityScan("com.bank.accounts.entity")
*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Bank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Saurabh Kumar",
						email = "saurabhtest@email.com",
						url = "test.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "test.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank Accounts microservice REST API Documentation",
				url = "test.com"
		),
		extensions = @Extension(properties = {}),
		security = @SecurityRequirement(name = ""),
		servers = @Server(),
		tags = @Tag(name = "Accounts")
)
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
