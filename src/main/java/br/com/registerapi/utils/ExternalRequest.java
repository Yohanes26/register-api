package br.com.registerapi.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import br.com.registerapi.model.GeolocationWrapper;
import br.com.registerapi.model.WeatherWrapper;
import br.com.registerapi.model.WoeidWrapper;

public class ExternalRequest {

	Gson gson = new Gson();
	
	@SuppressWarnings({ "resource", "deprecation" })
	public GeolocationWrapper getGeolocation(String ip) {
		final String url = "https://ipvigilante.com/" + ip;

		HttpGet get = new HttpGet(url);

		HttpResponse response = null;
		
		HttpClient httpClient = new DefaultHttpClient();
	    try {
			response = httpClient.execute(get);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String resp = null;
		try {
			resp = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gson.fromJson(resp, GeolocationWrapper.class);
	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	public WoeidWrapper getWoeid(String latitude, String longitude) {
		final String url = "https://www.metaweather.com/api/location/search/?lattlong="+ latitude +"," + longitude;

		HttpGet get = new HttpGet(url);

		HttpResponse response = null;
		
		HttpClient httpClient = new DefaultHttpClient();
	    try {
			response = httpClient.execute(get);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String resp = null;
		try {
			resp = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gson.fromJson(resp, WoeidWrapper.class);
	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	public WeatherWrapper getWeather(Long woeid) {
		final String url = "https://www.metaweather.com/api/location/" + woeid;

		HttpGet get = new HttpGet(url);

		HttpResponse response = null;
		
		HttpClient httpClient = new DefaultHttpClient();
	    try {
			response = httpClient.execute(get);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String resp = null;
		try {
			resp = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gson.fromJson(resp, WeatherWrapper.class);
	}
}
