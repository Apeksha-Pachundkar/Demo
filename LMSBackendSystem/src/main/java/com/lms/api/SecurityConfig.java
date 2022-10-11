package com.lms.api;

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

import com.lms.api.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	  @Autowired private MyUserDetailsService myUserDetailsService;
	 
	/*	
      Used by the default implementation of authenticationManager() 
       to attempt to obtain an AuthenticationManager.
	 */
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthProvider());
	/*
	 * auth.inMemoryAuthentication().withUser("harry").password(getPasswordEncoder()
	 * . encode("potter")).authorities("ADMIN") .and()
	 * .withUser("ronald").password(getPasswordEncoder().encode("weasley")).
	 * authorities( "USER");
	 */
		 
	}

	private AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(myUserDetailsService);
		auth.setPasswordEncoder(getPasswordEncoder());
		return auth;
	}
	
	@Override //Override this method to configure HttpSecurity
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/register").permitAll()
		                        .antMatchers(HttpMethod.GET,"/user/login").authenticated()
		                        .antMatchers(HttpMethod.PUT,"/user/update/**").authenticated()
		                        .antMatchers(HttpMethod.GET,"/learning-track").permitAll()
		                        .antMatchers(HttpMethod.POST,"/learningtrack").authenticated()
		                        .antMatchers(HttpMethod.GET,"/learningtrack").authenticated()
		                        .antMatchers(HttpMethod.POST,"/course/{lid}").authenticated()
		                        .antMatchers(HttpMethod.GET,"/course").authenticated()
		                        .antMatchers(HttpMethod.POST,"/author").authenticated()
		                        .antMatchers(HttpMethod.POST,"/review/**").authenticated()
		                        .antMatchers(HttpMethod.GET,"/review/**").permitAll()
		                        .antMatchers(HttpMethod.GET,"/reviews").permitAll()
		                        .antMatchers(HttpMethod.GET,"/review/sortedbyRatingsDESC/**").permitAll()
		                        .antMatchers(HttpMethod.PUT,"/review/**").authenticated()
		                        .antMatchers(HttpMethod.GET,"/review/stats/**").authenticated()
		                        .antMatchers(HttpMethod.POST,"/video/**").authenticated()
		                        .antMatchers(HttpMethod.POST,"/module/**").authenticated()
		                        .antMatchers(HttpMethod.GET,"/module/**").permitAll()
		                        .antMatchers(HttpMethod.GET,"/module/alternate/**").permitAll()
		                        .antMatchers(HttpMethod.GET,"/course/video/stats/**").permitAll()
		                        .antMatchers(HttpMethod.POST,"/forum").authenticated()
		                        .antMatchers(HttpMethod.POST,"/question").authenticated()
		                        .antMatchers(HttpMethod.POST,"/answer").authenticated()
		                        .antMatchers(HttpMethod.POST,"/forum/question/**/**").authenticated()
		                        .antMatchers(HttpMethod.POST,"/question/answer/**/**").authenticated()
		                        .antMatchers(HttpMethod.PUT,"/answer/**").authenticated()
		                        .antMatchers(HttpMethod.PUT,"/question/**").authenticated()
		                        .antMatchers(HttpMethod.GET,"/forum/stats").permitAll()
		                        .antMatchers(HttpMethod.PUT,"/answer/likes/**").authenticated()
		                        .antMatchers(HttpMethod.PUT,"/question/likes/**").authenticated()
		                        .anyRequest().permitAll()
				                .and().httpBasic()
				                .and().csrf()
				                .disable();

	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		PasswordEncoder passEncoder=new BCryptPasswordEncoder();
		return passEncoder;
	}
}
