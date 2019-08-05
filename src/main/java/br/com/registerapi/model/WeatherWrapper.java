package br.com.registerapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherWrapper {

	@JsonProperty("consolidated_weather")
	private List<WeatherModel> consolidatedWeather = new ArrayList<WeatherModel>();

	public List<WeatherModel> getConsolidatedWeather() {
		return consolidatedWeather;
	}

	public void setConsolidatedWeather(List<WeatherModel> consolidatedWeather) {
		this.consolidatedWeather = consolidatedWeather;
	}	
}
