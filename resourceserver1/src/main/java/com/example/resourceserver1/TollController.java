package com.example.resourceserver1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TollController {

	private List<TollData> tollData;

	public TollController() {
		tollData = new ArrayList<TollData>();
		tollData.add(new TollData("900", "BG45-KO", "2024-11-10-2024T01:23:17"));
		tollData.add(new TollData("901", "VA111-YU", "2024-11-10-2024T01:23:17"));
		tollData.add(new TollData("902", "UE228-UP", "2024-11-10-2024T01:23:17"));
		
	}
	
	@RequestMapping("/api/tolldata")
	public List<TollData> getTollData() {
		return tollData;
	}
}
