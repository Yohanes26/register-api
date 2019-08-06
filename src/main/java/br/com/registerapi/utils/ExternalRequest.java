package br.com.registerapi.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import br.com.registerapi.model.GeolocationWrapper;
import br.com.registerapi.model.WeatherModel;

@Service
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
	public Integer getWoeid(String latitude, String longitude) {
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
		
		resp = "{\"list\":"+resp+"}";
		List<LinkedHashMap<String, List<String>>> listModel = new ArrayList<LinkedHashMap<String, List<String>>>();
		listModel = JsonPath.read(resp, "$.list");
		Integer woeid = null;	
		
		for (Entry<String, List<String>> entry : listModel.get(0).entrySet()) {
			String key = entry.getKey();
		    if (key.equals("woeid")) {
		    	String valueSet = entry.toString();
		    	valueSet = valueSet.replace("woeid=", "");
		    	return Integer.parseInt(valueSet);
		    }
		}
		
		return woeid;

	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	public WeatherModel getWeather(Long woeid) {
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
		
		WeatherModel weatherModel = new WeatherModel();
		
		List<LinkedHashMap<String, List<String>>> listModel = new ArrayList<LinkedHashMap<String, List<String>>>();
		listModel = JsonPath.read(resp, "$.consolidated_weather");
		
		for (Entry<String, List<String>> entry : listModel.get(0).entrySet()) {
			String key = entry.getKey();
			    if (key.equals("id")) {
			    	String valueSet = entry.toString();
			    	valueSet = valueSet.replace("id=", "");
			    	weatherModel.setId(new Long(valueSet));
			    }
			    if (key.equals("min_temp")) {
			    	String valueSet = entry.toString();
			    	valueSet = valueSet.replace("min_temp=", "");
			    	weatherModel.setMinTemp(valueSet);
			    }
			    if (key.equals("max_temp")) {
			    	String valueSet = entry.toString();
			    	valueSet = valueSet.replace("max_temp=", "");
			    	weatherModel.setMaxTemp(valueSet);
			    }
		}
		
		return weatherModel;
	}
}
