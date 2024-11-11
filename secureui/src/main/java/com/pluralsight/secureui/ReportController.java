package com.pluralsight.secureui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Controller
public class ReportController {

	@Autowired
	private WebClient webClient; //autowire to bean in SecurityCOnfig class which is set for ouath2, so he takes care of 
								// access tokens etc to be included into HTTP header requests calls toward (downstream) external servers. 
	
	@RequestMapping("/")
	public String loadHome() {
		return "home";
	}
	
	@RequestMapping("/report")
	public String loadReport(Model m) {
		
		Flux<TollData> response = this.webClient.get()
				.uri("http://localhost:8081/api/tolldata")
				.retrieve()
				.bodyToFlux(TollData.class);
		
		List<TollData> tolldata = response.collectList().block();
		
		m.addAttribute("tolldata", tolldata);
		return "report";
	}
}
