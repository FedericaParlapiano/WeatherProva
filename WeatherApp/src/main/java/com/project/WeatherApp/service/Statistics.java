package com.project.WeatherApp.service;

import org.json.JSONObject;

public interface Statistics {
	public abstract JSONObject todayAverage(String name);
}
