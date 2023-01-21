package com.spring.backend.easyvet.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * SpringSecurity Configuration.
 * 
 * @author Andrés.
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsList.add(User.withUsername("sebastian").password(this.passwordEncoder.encode("password"))
				.roles("VETERYNAY").build());
		userDetailsList.add(User.withUsername("andres").password(this.passwordEncoder.encode("password"))
				.roles("USER").build());

		return new InMemoryUserDetailsManager(userDetailsList);
	}
	
	@Bean
    public DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable();    	
        return http.build();
    }

}
