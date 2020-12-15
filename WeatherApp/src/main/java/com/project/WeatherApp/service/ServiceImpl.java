/**
 * 
 */
package com.project.WeatherApp.service;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.JSONArray;
import org.json.simple.parser.*;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import com.project.WeatherApp.model.*;



/**
 * @author Federica
 * @author Francesca
 */

@Service

public class ServiceImpl implements com.project.WeatherApp.service.Service {
	
	private String api_key = "666efac3e1caf3f728f8c5860edeb469";
	
	
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
		
		City city = getCityWeatherRistrictfromApi(cityName);		
		
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
		
		obj = tojson.parser(city);
		//String myJsonObjectSerialized = obj.toString();
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
		
		String nomeFile = cityName+today;
		
		String path = "C:/Users/feder/eclipse-workspace/"+nomeFile+".txt";
		
		
        ObjectOutputStream outputStream = null;
        try{
        	outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(obj.toString());
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
		
		String date = "";
		date += (city.getVector()[0].getData()).charAt(8);
		date += (city.getVector()[0].getData()).charAt(9);
	
		String effectiveDate = date;
		
		while( date.equals(effectiveDate) ) {
			temp_max_ave += city.getVector()[i].getTemp_max();
			temp_min_ave += city.getVector()[i].getTemp_min();
			feels_like_ave += city.getVector()[i].getFeels_like();
			visibility_ave += city.getVector()[i].getVisibility();
			i++;
			effectiveDate = "";
			effectiveDate += (city.getVector()[i].getData()).charAt(8);
			effectiveDate += (city.getVector()[i].getData()).charAt(9);
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
	
	
	public JSONObject fiveDayAverage(String name) {
		 
		
		City city = new City(name);
		city = getCityWeatherRistrictfromApi(name);
		
		double temp_max_ave = 0;
		double temp_min_ave = 0;
		double feels_like_ave = 0;
		double visibility_ave = 0;
		
		int i=0;
		
		while( i<city.getVector().length ) {
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
	
	public JSONArray statsHistory(String name1) throws IOException, ParseException {
		
		JSONObject jsonObject1;
		JSONObject jsonObject2;
		
		String path = "C:/Users/feder/eclipse-workspace/WeatherApp/WeatherApp/prova.txt";
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		String json = "";
		try {
		    StringBuilder sb1 = new StringBuilder();
		    String line = reader.readLine();

		    while (line != "\n") {
		        sb1.append(line);
		        sb1.append("\n");
		        line = reader.readLine();
		    }
		    json = sb1.toString();
		    jsonObject1 = new JSONObject(json);
		    json = "";
		    
		    StringBuilder sb2 = new StringBuilder();
		    while (line != null) {
		        sb2.append(line);
		        sb2.append("\n");
		        line = reader.readLine();
		    }
		    json = sb2.toString();
		    jsonObject2 = new JSONObject(json);
		} finally {
		    reader.close();
		}
		
		JSONArray arr = new JSONArray();
		arr.put(jsonObject1);
		arr.put(jsonObject2);
		
		return arr;
		
		
	}

	
	
	/*
	public JSONObject statsHistory2(String name1, String name2) throws IOException, ParseException {
		
		JSONObject jsonObject1;
		JSONObject jsonObject2;
		
		String path = "C:/Users/feder/eclipse-workspace/WeatherApp/WeatherApp/prova.txt";
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		String json = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = reader.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append("\n");
		        line = reader.readLine();
		    }
		    json = sb.toString();
		} finally {
		    reader.close();
		}
		
		System.out.println(json);
		
		/*
		JSONParser parser = new JSONParser(); 
		object = (JSONObject) parser.parse(json);
		
		try {
		     jsonObject1 = new JSONObject(json);
		     jsonObject2 = new JSONObject(json);
		     JSONObject jsonObject = new JSONObject();
		     jsonObject.put("1", jsonObject1);
		     jsonObject.put("2", jsonObject2);
		     
		     return jsonObject;
		}catch (JSONException err){
		}
		
		JSONObject obj = new JSONObject();
		return obj;
		
		
	}
	*/
	
	
}
