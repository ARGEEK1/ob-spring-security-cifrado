package com.example.obspringsecuritycifrado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ObSpringSecurityCifradoApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObSpringSecurityCifradoApplication.class, args);

		UserRepository repository = context.getBean(UserRepository.class);
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);

		User user1 = new User(null,"Jose",encoder.encode("admin"));
		User user2 = new User(null,"Pepe", encoder.encode("admin1"));
		User user3 = new User(null,"Jorge", encoder.encode("admin2"));

		repository.save(user1);
		repository.save(user2);
		repository.save(user3);

	}

}
