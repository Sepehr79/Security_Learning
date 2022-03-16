package com.sepehr.security_learning;

import com.sepehr.security_learning.model.entity.Authority;
import com.sepehr.security_learning.model.entity.User;
import com.sepehr.security_learning.security.user.UserDetailsSecurityManager;
import com.sepehr.security_learning.security.user.UserSecurityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SecurityLearningApplication implements CommandLineRunner {

	private final UserDetailsSecurityManager userDetailsSecurityManager;

	public static void main(String[] args) {
		SpringApplication.run(SecurityLearningApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
				.name("sepehr")
				.lastName("mollaei")
				.userName("sepehr79")
				.password("12345")
				.build();
		user.addAuthority(new Authority("ADMIN"));

		userDetailsSecurityManager.createUser(new UserSecurityDetails(user));
	}
}
