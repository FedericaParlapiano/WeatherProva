/**
 * 
 */
package com.project.WeatherApp.model;

/**
 * @author Federica
 * @autor Francesca
 */

import java.util.Vector;

public class City {
	
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	protected String country;
	private Vector<Weather> weatherArray = new Vector<Weather>();
	
	
	
	public City() {
		super();
	}

	public City(long id) {
		this.id=id;
		this.name=null;
		this.coordinates=null;
		this.country=null;
		//this.weatherArray = null;
	}
	
	public City(String name) {
		this.id=0;
		this.name=name;
		this.coordinates=null;
		this.country=null;
		//this.weatherArray = null;
	}
	
	
	public City(Coordinates coordinates) {
		this.id=0;
		this.name=null;
		this.coordinates=coordinates;
		this.country=null;
		//this.weatherArray = null;
	}
	
	public City(String name, String country) {
		this.id=0;
		this.name=name;
		this.coordinates=null;
		this.country=country;
		//this.weatherArray = null;
	}

	
	public City(long id, String name, Coordinates coordinates, String country,
			int population, int timezone, String sunrise, String sunset) {
		this.id = id;
		this.name = name;
		this.coordinates = coordinates;
		this.country = country;
		//this.weatherArray = null;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Vector<Weather> getWeatherArray() {
		return weatherArray;
	}

	public void addWeather (Weather e) {
		weatherArray.add(e);
	}
	

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", country=" + country
				+ ", weatherArray=" + weatherArray + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}