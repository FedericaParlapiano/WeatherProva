/**
 * 
 */
package com.project.WeatherApp.model;

import java.io.Serializable;

/**
 * @author Federica
 * @author Francesca
 */
public class Weather implements Serializable {
	
	static final long serialVersionUID = 1;
	
	private String main;
	private String description;
	private int visibility;
	private double temp_max;
	private double temp_min;
	private double feels_like;
	private String data;
	
	public String getMain() {
		return main;
	}
	
	
	
	public Weather() {
		this.main = null;
		this.description = null;
		this.visibility = 0;
		this.temp_max = 0;
		this.temp_min = 0;
		this.feels_like = 0;
		this.data = null;
	}
	
	

	public Weather(String main, String description) {
		super();
		this.main = main;
		this.description = description;
	}
	


	public Weather(String main, String description, int visibility, double temp_max, double temp_min) {
		super();
		this.main = main;
		this.description = description;
		this.visibility = visibility;
		this.temp_max = temp_max;
		this.temp_min = temp_min;
	}



	public Weather(String main, String description, int visibility, double temp_max, double temp_min, double feels_like,
			String data) {
		super();
		this.main = main;
		this.description = description;
		this.visibility = visibility;
		this.temp_max = temp_max;
		this.temp_min = temp_min;
		this.feels_like = feels_like;
		this.data = data;
	}



	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	public double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	public double getFeels_like() {
		return feels_like;
	}
	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(feels_like) != Double.doubleToLongBits(other.feels_like))
			return false;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		if (Double.doubleToLongBits(temp_max) != Double.doubleToLongBits(other.temp_max))
			return false;
		if (Double.doubleToLongBits(temp_min) != Double.doubleToLongBits(other.temp_min))
			return false;
		if (visibility != other.visibility)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "data=" + data + "main=" + main + ", description=" + description + ", visibility=" + visibility + ", temp_max="
				+ temp_max + ", temp_min=" + temp_min + ", feels_like=" + feels_like + "";
	}
	
	
	
	

}
