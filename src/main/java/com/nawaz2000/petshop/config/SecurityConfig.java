package com.nawaz2000.petshop.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nawaz2000.petshop.service.MyUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	private UserBuilder user = User.withDefaultPasswordEncoder();
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
		
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
					.loginPage("/login")
//					.loginProcessingUrl("/authenticate");
					.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
