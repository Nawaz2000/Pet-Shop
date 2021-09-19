package com.nawaz2000.petshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserBuilder user = User.withDefaultPasswordEncoder();

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(user.username("Nawaz2000").password("123").roles("ADMIN"))
			.withUser(user.username("Thorkell").password("123").roles("USER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/js/**", "/css/**").permitAll()
		.antMatchers("/orders", "/cart", "/account").authenticated()
//		.anyRequest().authenticated()
//			.antMatchers("/show*").hasAnyRole("MANAGER", "ADMIN", "EMPLOYEE")
//			.antMatchers("/add*").hasAnyRole("MANAGER", "ADMIN")
//			.antMatchers("/delete").hasRole("ADMIN")
//			.antMatchers("/**").hasRole("EMPLOYEE")
//			.antMatchers("/resources/**")
//			.permitAll()
			.and()
				.formLogin()
					.loginPage("/showLoginPage")
					.loginProcessingUrl("/authenticate")
					.permitAll()
			.and()
				.logout()
					.logoutUrl("/logout")
					.permitAll();
	}
	
}
