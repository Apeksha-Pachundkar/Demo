package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthProvider());

		/*
		 * auth.inMemoryAuthentication().withUser("harry").password(getPassEncoder().
		 * encode("potter")).authorities("MANAGER") .and()
		 * .withUser("ronald").password(getPassEncoder().encode("weasley")).authorities(
		 * "ACCOUNTANT");
		 */

	}

	private AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(myUserDetailsService);
		auth.setPasswordEncoder(getPassEncoder());
		return auth;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		                .antMatchers(HttpMethod.POST, "/user").permitAll()
		                .antMatchers("/employee").permitAll()//(working) 
				        //.antMatchers("/employee/{id}").hasAnyAuthority("Sr Developer") //(working) authority is given only to the manager
				        .antMatchers(HttpMethod.GET,"/employee/city").authenticated()
				        .antMatchers("/employee/salary").authenticated()
				        .antMatchers("/employee/age").hasAnyAuthority("Jr Developer")
				        .antMatchers("/employee/department/{did}").authenticated()
				        .antMatchers("/hello/public").permitAll()
				        .antMatchers("/hello").authenticated()
				        .and()
				        .httpBasic()
				        .and()
				        .csrf().disable();

	}

	@Bean
	public PasswordEncoder getPassEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;

	}
}
