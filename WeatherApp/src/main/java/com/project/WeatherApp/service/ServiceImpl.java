/**
 * 
 */
package com.project.WeatherApp.service;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
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
	
	private static final OpenOption APPEND = null;
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
	
	
	

	public JSONArray getVisibilityfromApi(String name) {
	
		JSONObject object = getCityWeather(name);
		JSONArray toGive = new JSONArray();
			
			JSONArray weatherArray = object.getJSONArray("list");
			JSONObject support;
			int visibility;
			String data;
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				support = weatherArray.getJSONObject(i);
				visibility = (int) support.get("visibility");
				data = (String) support.get("dt_txt");
				JSONObject toReturn = new JSONObject();
				toReturn.put("Visibility", visibility);
				toReturn.put("Data", data);
				toGive.put(toReturn);
				
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
				vector[i] = new Weather();
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
		
		
		city.setVector(vector);
		
		
		
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
	
	
	public String save(String cityName) throws IOException {
		
		String nomeFile = "info.txt";
		
		City city = getCityWeatherRistrictfromApi(cityName);		
		
		ToJSON tojson = new ToJSON();
		JSONObject object = tojson.parser(city);
		String myJsonObjectSerialized = object.toString();
		
		String path = "C:/Users/feder/eclipse-workspace/fileInfo.txt";
		
		
        ObjectOutputStream outputStream = null;
        try{
        	outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(myJsonObjectSerialized);
            outputStream.flush();
            outputStream.close();
         }
        catch (Exception e){
        System.err.println("Error: " + e);
        }
        
        return nomeFile;
		
	}
	
	
	public JSONObject todayAverage(String name) {
		 
		
		City city = new City(name);
		city = getCityWeatherRistrictfromApi(name);
		
		double temp_max_ave = 0;
		double temp_min_ave = 0;
		double feels_like_ave = 0;
		double visibility_ave = 0;
		
		int i=0;
		
		//((Character.toString((weather[i].getData()).charAt(0))) + (Character.toString((weather[i].getData()).charAt(1)))).equals(day)
		while( i<8 ) {
			temp_max_ave += city.getVector()[i].getTemp_max();
			temp_min_ave += city.getVector()[i].getTemp_min();
			feels_like_ave += city.getVector()[i].getFeels_like();
			visibility_ave += city.getVector()[i].getVisibility();
			i++;
		}
			
		temp_max_ave = temp_max_ave/i;
		temp_min_ave = temp_min_ave/i;
		feels_like_ave = feels_like_ave/i;
		visibility_ave = visibility_ave/i;
		
		JSONObject object = new JSONObject();
		
		object.put("CityName", name);
		object.put("Temp_Max Average", temp_max_ave);
		object.put("Temp_Min Average", temp_min_ave);
		object.put("Feels_like Average", feels_like_ave);
		object.put("Visibility Average", visibility_ave);
		
		
		return object;
	}
	
	
	
	
}
