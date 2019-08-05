package br.com.registerapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeolocationWrapper {

	@JsonProperty("data")
	private GeolocationModel data;

	public GeolocationModel getData() {
		return data;
	}

	public void setData(GeolocationModel data) {
		this.data = data;
	}
}
