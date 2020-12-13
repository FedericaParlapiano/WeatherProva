/**
 * 
 */
package com.project.WeatherApp.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.*;

import com.project.WeatherApp.service.Service;

/**
 * @author feder
 *
 */

@RestController

public class Controller {
	
	@Autowired
	Service service;
	
	/*
	@GetMapping("/City")
	public JSONObject getCity() {
		return service.getCityWeather("Tolentino");
	}
	*/
	
	@GetMapping(value="/city")
    public ResponseEntity<Object> getCity2(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getCityWeather(cityName).toString(), HttpStatus.OK);
    }
	
	/*
	@GetMapping("/City")
	public JSONObject getCity() {
		
		JSONObject obj = null;
		
		obj.put("country", "IT");
		obj.put("name", "Termoli");
		
		return obj;
	}
	
	*/
	
	/*
	@GetMapping("/City")
	public JSONObject getCity(@RequestParam(name="name", defaultValue="none") String name) {
		
		return service.getCityWeather(name);
	}
	
	/*
	
	@RequestMapping(value="City​​", method = RequestMethod.GET)
	public JSONObject getCity(@RequestParam(value="city") String city) {​​
		return service.getCityWeather(city);
	}
	
	/*
	@GetMapping("/City")
	public ResponseEntity<Object> getCity(@RequestParam(name="name", defaultValue="none") String name) {​​​​
		return new ResponseEntity<>(service.getCityWeather(name), HttpStatus.OK);
	}
	*/
	/*
	@RequestMapping(value="/City")
	public ResponseEntity<Object> getCity() {
		return new ResponseEntity<> (service.getCityWeather("Termoli"),HttpStatus.OK);
	}
	*/
	
}
