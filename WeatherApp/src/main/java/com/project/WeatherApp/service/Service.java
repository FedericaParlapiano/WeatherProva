/**
 * 
 */
package com.project.WeatherApp.service;

import org.json.JSONObject;

/**
 * @author feder
 *
 */
public interface Service {
	public abstract JSONObject getCityWeather(String name);
	public abstract String getVisibilityfromApi(String city);
}
