package com.project.WeatherApp.service;

import org.json.JSONObject;

import com.project.WeatherApp.model.City;

public class StatisticsImpl implements Statistics {
	
	Service service;
	
	
	public JSONObject todayAverage(String name) {
		 
		
		City city = new City(name);
		city = service.getCityWeatherRistrictfromApi(name);
		
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
