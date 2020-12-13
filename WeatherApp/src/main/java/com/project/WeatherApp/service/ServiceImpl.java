/**
 * 
 */
package com.project.WeatherApp.service;

import java.util.*;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONString;
import org.json.simple.parser.*;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.WeatherApp.model.*;


/**
 * @author feder
 *
 */

@Service

public class ServiceImpl implements com.project.WeatherApp.service.Service {
	
	private String api_key = "666efac3e1caf3f728f8c5860edeb469";
	/*
	public String[] getInfo () {​​​​
		JSONObject obj;
		String url = "api.openweathermap.org/data/2.5/forecast?q={​​​​city name}​​​​&appid={​​​​API key}​​​​";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type","application/json");
		//headers.set("Authorization", "Bearer " + api_key);
		headers.set("city name","name"); //devo aggiungere il nome della città al corpo della richiesta su post
		RestTemplate restTemplate = new RestTemplate();
	}
	*/
	
	//va a prendere le previsioni meteo di una città
	
	
	public JSONObject getCityWeather(String city) {
		
		JSONObject obj;
		String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		
		return obj;
		
	}
	
	/*
	public static void main(String[] args) { 
		ServiceImpl a = new ServiceImpl();  
		a.getCityWeather("Tolentino"); 
	}
	*/
	
	public String getVisibilityfromApi(String city) {
		JSONParser parser = new JSONParser(); 
		JSONObject object = null;
		String country = null;
		
		String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + api_key;
		
		RestTemplate rt = new RestTemplate();
		
		String result = rt.getForObject(url,String.class);
		
		try {
			object = (JSONObject) parser.parse(result);
			
			country = (String) object.get("country");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return country;
		
	}
	
	
	
}
