package com.techjs.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techjs.springsecurity.configure.SecurityConfig;
import com.techjs.springsecurity.model.User;
import com.techjs.springsecurity.persistence.InMemoryUserRepository;
import com.techjs.springsecurity.persistence.UserRepository;

@SpringBootApplication
@ComponentScan("com.techjs.springsecurity")
public class SpringsecurityApplication {

	@Bean
	public UserRepository userRepository() {
		return new InMemoryUserRepository();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Converter<String, User> messageConverter() {
		return new Converter<String, User>() {

			@Override
			public User convert(String id) {
				return userRepository().findUser(Long.valueOf(id));
			}

		};
	}

	public static void main(String[] args) {
//		SpringApplication.run(SpringsecurityApplication.class, args);
		SpringApplication.run(new Class[] { SpringsecurityApplication.class, SecurityConfig.class }, args);

	}

}
