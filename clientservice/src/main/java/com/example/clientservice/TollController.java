package com.example.clientservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TollController {

	@Autowired
	private WebClient webClient;
	
	@GetMapping("/")
	public Mono<TollData> getLatestToll(){
		// call downstream service
		Flux<TollData> response = this.webClient.get()
				.uri("http://localhost:8081/api/tolldata")
				.retrieve()
				.bodyToFlux(TollData.class);
		
		List<TollData> tolldata = response.collectList().block();
		
		return Mono.just(tolldata.get(0));
	}
}
