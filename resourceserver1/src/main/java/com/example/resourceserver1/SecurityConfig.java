package com.example.resourceserver1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			//.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/api/tolldata")
						.hasAuthority("SCOPE_reader")
					.anyRequest()
						.authenticated()
					)
			.oauth2ResourceServer()
				.jwt();
		return http.build();
	}
}
