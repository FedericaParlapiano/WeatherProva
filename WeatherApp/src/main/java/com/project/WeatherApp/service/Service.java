/**
 * 
 */
package com.project.WeatherApp.service;

import com.project.WeatherApp.model.*;
import org.json.JSONObject;

import java.io.IOException;

import org.json.JSONArray;

/**
 * @author feder
 *
 */
public interface Service {
	public abstract JSONObject getCityWeather(String city);
	public abstract City getCityInfofromApi(String city);
	public abstract JSONArray getVisibilityfromApi(String city);
	public abstract City getCityWeatherRistrictfromApi(String city);
	public abstract String save(String city) throws IOException;
	public abstract JSONObject todayAverage(String name);
}
