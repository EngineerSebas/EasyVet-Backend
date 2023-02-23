package com.spring.backend.easyvet.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.backend.easyvet.security.CustomPropietorAndVeterinaryDetailsService;
import com.spring.backend.easyvet.security.JwtAuthenticationEntryPoint;
import com.spring.backend.easyvet.security.JwtAuthenticationFilter;

/**
 * SpringSecurity Configuration.
 * 
 * @author Andrés.
 */

@EnableWebSecurity
@ComponentScan(basePackages = "com.spring.backend.easyvet")
@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private CustomPropietorAndVeterinaryDetailsService customDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/pet/**").hasAuthority("ROLE_PROPIETOR")
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.headers(headers -> headers.frameOptions().sameOrigin())
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
			
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder build) throws Exception{
		build.userDetailsService(customDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
