package br.com.registerapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherWrapper {

	@JsonProperty("consolidated_weather")
	private List<WeatherModel> consolidated_weather = new ArrayList<WeatherModel>();

	public List<WeatherModel> getConsolidatedWeather() {
		return consolidated_weather;
	}

	public void setConsolidatedWeather(List<WeatherModel> consolidated_weather) {
		this.consolidated_weather = consolidated_weather;
	}	
}
