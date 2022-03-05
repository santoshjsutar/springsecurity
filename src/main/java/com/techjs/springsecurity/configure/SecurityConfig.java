package com.techjs.springsecurity.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception { // @formatter:off
		authenticationManagerBuilder
			.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder)	
			.withUser("santoshjs")
			.password(passwordEncoder.encode("pass"))
			.roles("USER");
	} // @formatter:on

	@Override
	protected void configure(HttpSecurity http) throws Exception { // @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/delete/**").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/doLogin")
				
				.and()
//				.logout().permitAll().logoutUrl("/doLogout")
				.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/doLogout","GET"))
//				.logoutSuccessHandler(null) // handle custom logout page & actions
//				.clearAuthentication(true) // default = true
//				.deleteCookies()
//				.invalidateHttpSession(false)
				
			.and()
			.csrf().disable()
			;

	} // @formatter:on

}
