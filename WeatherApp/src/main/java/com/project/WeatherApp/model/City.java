/**
 * 
 */
package com.project.WeatherApp.model;

import java.io.Serializable;

/**
 * @author Federica
 * @autor Francesca
 */



public class City implements Serializable {
	
	static final long serialVersionUID = 1;

	private String name;
	private String country;
	private long id;
	private Coordinates coordinates;
	//private Vector<Weather> weatherArray = new Vector<Weather>();
	private Weather[] vector = new Weather[40];
	
	
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
	

	public Weather[] getVector() {
		return vector;
	}

	public void setVector(Weather[] vector) {
		this.vector = vector;
	}
	
	public String toStringVector() {
		String toReturn="";
		for (int i=0; i<vector.length; i++)
			toReturn += vector[i].toString();
		return toReturn;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", country=" + country
				+ ", weatherArray=" + toStringVector() + "";
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