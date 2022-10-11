package com.externalapidemo.consumeapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {

	@Autowired
	private RestTemplate restTemplate;
	
	private static String url = "https://restcountries.com/v3.1/all";
	private static String urlnew ="https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";
	@GetMapping("/countries")
	public List<Object> getCountries(){
		Object[] countries = restTemplate.getForObject(url, Object[].class);
	    return Arrays.asList(countries);	
	}
	
	
		@GetMapping("/coronaUpdates")
	public Object getCoronaVirusUpdates(){
		Object coronaStatus = restTemplate.getForObject(urlnew, Object.class);
	    return Arrays.asList(coronaStatus);	
	}
}
