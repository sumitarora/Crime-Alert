package com.crimealert.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.crimealert.model.Crime;
import com.crimealert.model.User;
import com.crimealert.service.CrimeService;
import com.crimealert.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class UploadDataUtils {

	@Autowired
	UserService userService;
	
	@Autowired
	CrimeService crimeService;
	
	public UploadDataUtils() {
		log.debug("inside file upload");		
	}
	
	public void uploadData() throws InterruptedException {
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/sarora/Desktop/temp/crime_2011.csv")))
		{
			String sCurrentLine; 
			final User user = userService.getUserByEmail("admin@gmail.com");
			while ((sCurrentLine = br.readLine()) != null) {
				Thread.sleep(100);
				String[] values = sCurrentLine.split(",");
			
				try {
					RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
					String url = "http://maps.googleapis.com/maps/api/geocode/json?address="+values[3]+",Vancouver,Canada&sensor=true";
					ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode actualObj = mapper.readValue(result.getBody(), JsonNode.class);
					log.debug("response: {}", actualObj);
					
					Crime c = new Crime();
					c.setOpendata(true);
					c.setAddress(actualObj.get("results").get(0).get("formatted_address").textValue());
					c.setAdministrative_area_level_1(actualObj.get("results").get(0).get("address_components").get(4).get("long_name").textValue());
					c.setCountry(actualObj.get("results").get(0).get("address_components").get(5).get("long_name").textValue());
					c.setCrimeDate(new Date(new java.util.Date().getTime()));
					c.setDescription(values[0]);
					c.setLatitude(actualObj.get("results").get(0).get("geometry").get("location").get("lat").toString());
					c.setLocality(actualObj.get("results").get(0).get("address_components").get(2).get("long_name").textValue());
					c.setLongitude(actualObj.get("results").get(0).get("geometry").get("location").get("lng").toString());
					c.setMap("map");
					c.setTitle(values[0]);
					c.setUser(user);
					
					crimeService.saveCrime(c);
					log.debug(result.toString());
				} catch (Exception ex) {
					
				}
				

			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
 		
	}
	
}
