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
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		
		return obj;
		
	}
	
	/*
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
	/*
	 * 
	 */
	
	
	/*
	 * 
	 BUONOOOO!
	public String getVisibilityfromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		city = getCityInfofromApi(name);
		String result = "";
		
		try {
			
			JSONArray weatherArray = object.getJSONArray("list");
			JSONObject support;
			int visibility;
			String data;
			
			for (int i = 0; i<weatherArray.length(); i++) {
				support = weatherArray.getJSONObject(i);
				visibility = (int) support.get("visibility");
				data = (String) support.get("dt_txt");
				result += visibility + " " + data + "\n";
			}
	
		} catch(Exception e) {
			return "ERRORE!";
		}
		
		return result;
		
	}
	*/
	
	
	
	public JSONArray getVisibilityfromApi(String name) {
	
		JSONObject object = getCityWeather(name);
		JSONArray toGive = new JSONArray();
		
		try {
			
			JSONArray weatherArray = object.getJSONArray("list");
			JSONObject support;
			JSONObject toReturn = new JSONObject();
			int visibility;
			String data;
			
			for (int i = 0; i<weatherArray.length(); i++) {
				support = weatherArray.getJSONObject(i);
				visibility = (int) support.get("visibility");
				data = (String) support.get("dt_txt");
				toReturn.put("Visibility", visibility);
				toReturn.put("Data", data);
				toGive.put(toReturn);
			}
	
		} catch(Exception e) {
		}
		
		return toGive;
		
	}
	
	
	
	public City getCityWeatherRistrictfromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		city = getCityInfofromApi(name);
		
		Weather weather = new Weather();
		
		JSONArray weatherArray = object.getJSONArray("list");
		JSONObject counter;
		
		Weather[] vector = new Weather[weatherArray.length()];
		
		
		try {
			
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				counter = weatherArray.getJSONObject(i);
				weather.setVisibility(counter.getInt("visibility"));
				weather.setData(counter.getString("dt_txt"));
				JSONArray arrayW = counter.getJSONArray("weather");
				JSONObject objectW = arrayW.getJSONObject(0);
				weather.setDescription(objectW.getString("description"));
				weather.setMain(objectW.getString("main"));
				JSONObject objectW2 = counter.getJSONObject("main");
				weather.setTemp_max(objectW2.getDouble("temp_max"));
				weather.setTemp_min(objectW2.getDouble("temp_min"));
				weather.setFeels_like(objectW2.getDouble("feels_like"));
				vector[i].setData(weather.getData());
				vector[i].setVisibility(weather.getVisibility());
				vector[i].setTemp_max(weather.getTemp_max());
				vector[i].setTemp_min(weather.getTemp_min());
				vector[i].setFeels_like(weather.getFeels_like());
				vector[i].setDescription(weather.getDescription());
				vector[i].setMain(weather.getMain());
			}
	
		} catch(Exception e) {
		}
		
		for (int i=0; i<vector.length; i++) {
			System.out.println(vector[i].getData());
			System.out.println(vector[i].getVisibility());
			System.out.println(vector[i].getTemp_max());
			System.out.println(vector[i].getTemp_min());
			System.out.println(vector[i].getFeels_like());
			System.out.println(vector[i].getDescription());
			System.out.println(vector[i].getMain());
		}
		
		return city;
		
	}
	
	
	public City getCityInfofromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		try {
			
			JSONObject cityObj = object.getJSONObject("city");
			String country = (String) cityObj.get("country");
			int id = (int) cityObj.get("id");
			JSONObject coordinatesObj = cityObj.getJSONObject("coord");
			double latitude = (double) coordinatesObj.get("lat");
			double longitude = (double) coordinatesObj.get("lon");
			Coordinates coordinates = new Coordinates(latitude,longitude); 
			city.setCountry(country);
			city.setId(id);
			city.setCoordinates(coordinates);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return city;
		
	}
	
	
	
}
