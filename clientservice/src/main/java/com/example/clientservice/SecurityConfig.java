package com.example.clientservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {	
		http
			//.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/")
						.permitAll()
					);
		
		return http.build();
	}
	
	// load up client configurations
	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,
								OAuth2AuthorizedClientService authorizedClientService) {
		OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials()
				.build();
		
		AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager = 
				new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
		
		return authorizedClientManager;
	}
	
	
	// load up a webclient bean with the right client configuration
	@Bean
	WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {

		var oauth2Client = new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
		
		oauth2Client.setDefaultClientRegistrationId("keycloak");
		return WebClient.builder().apply(oauth2Client.oauth2Configuration()).build();
	}
}
