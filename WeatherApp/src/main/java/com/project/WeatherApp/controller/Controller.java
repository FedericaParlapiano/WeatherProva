/**
 * 
 */
package com.project.WeatherApp.controller;

import java.io.IOException;

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

import com.project.WeatherApp.model.*;
import com.project.WeatherApp.service.Service;
import com.project.WeatherApp.service.ToJSON;
import com.project.WeatherApp.utils.stats.Statistics;
import com.project.WeatherApp.utils.stats.StatisticsImpl;

/**
 * @author feder
 *
 */

@RestController

public class Controller {
	
	@Autowired
	Service service;
	StatisticsImpl statistic = new StatisticsImpl();
	
	@GetMapping(value="/city")
    public ResponseEntity<Object> getCity(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getCityWeather(cityName).toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/country")
    public ResponseEntity<Object> getCountry(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getCityInfofromApi(cityName).toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/visibility")
    public ResponseEntity<Object> getVisibility(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getVisibilityfromApi(cityName).toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/restrictCityWeather")
    public ResponseEntity<Object> getCityWeather(@RequestParam String cityName) {
		
		City city = service.getCityWeatherRistrictfromApi(cityName);
		
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
		
		obj = tojson.parser(city);
		
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/save")
    public ResponseEntity<Object> saveToAFile(@RequestParam String cityName) throws IOException {
		
		String nomeFile = service.save(cityName);
		
		return new ResponseEntity<> ("Salvato nel file"+nomeFile , HttpStatus.OK);
	}
	
	
	@GetMapping(value="/todayAverage")
    public ResponseEntity<Object> todayAverage(@RequestParam String cityName) throws IOException {
		
		JSONObject obj = new JSONObject();
		obj = service.todayAverage(cityName);
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
	}
	
	
	
	
	
	
}
