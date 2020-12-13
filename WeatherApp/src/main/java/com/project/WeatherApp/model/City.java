/**
 * 
 */
package com.project.WeatherApp.model;

/**
 * @author Federica
 * @autor Francesca
 */
import java.util.ArrayList;

public class City {
	
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	protected String country;
	private ArrayList<Weather> weather = new ArrayList<Weather>();
	
	public City() {
		this.id=0;
		this.name=null;
		this.coordinates=null;
		this.country=null;
		this.weather = null;
	}
	
	public City(long id) {
		this.id=id;
		this.name=null;
		this.coordinates=null;
		this.country=null;
		this.weather = null;
	}
	
	public City(String name) {
		this.id=0;
		this.name=name;
		this.coordinates=null;
		this.country=null;
		this.weather = null;
	}
	
	
	public City(Coordinates coordinates) {
		this.id=0;
		this.name=null;
		this.coordinates=coordinates;
		this.country=null;
		this.weather = null;
	}
	
	public City(String name, String country) {
		this.id=0;
		this.name=name;
		this.coordinates=null;
		this.country=country;
		this.weather = null;
	}

	
	public City(long id, String name, Coordinates coordinates, String country,
			int population, int timezone, String sunrise, String sunset) {
		this.id = id;
		this.name = name;
		this.coordinates = coordinates;
		this.country = country;
		this.weather = null;
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
	
	public boolean addWeather(Weather e) {
		if(weather.add(e))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", country=" + country;
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