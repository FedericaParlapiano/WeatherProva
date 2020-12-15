/**
 * 
 */
package com.project.WeatherApp.service;

import com.project.WeatherApp.model.*;
import org.json.JSONObject;
import org.json.JSONArray;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * @author Federica
 * @author Francesca
 */
public interface Service {
	public abstract JSONObject getCityWeather(String city);
	public abstract City getCityInfofromApi(String city);
	public abstract JSONArray getVisibilityfromApi(String city);
	public abstract City getCityWeatherRistrictfromApi(String city);
	public abstract String save(String city) throws IOException;
	public abstract JSONObject todayAverage(String name);
	public abstract JSONObject fiveDayAverage(String name);
	public abstract JSONArray statsHistory(String name1) throws IOException, ParseException;
}
