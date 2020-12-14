/**
 * 
 */
package com.project.WeatherApp.service;

import com.project.WeatherApp.model.*;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * @author feder
 *
 */
public interface Service {
	public abstract JSONObject getCityWeather(String name);
	public abstract City getCityInfofromApi(String city);
	public abstract JSONArray getVisibilityfromApi(String city);
	public abstract City getCityWeatherRistrictfromApi(String name);
}
