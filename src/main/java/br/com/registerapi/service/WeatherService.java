package br.com.registerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.registerapi.entity.WeatherEntity;
import br.com.registerapi.model.WeatherModel;
import br.com.registerapi.repository.WeatherRepository;
import br.com.registerapi.utils.ExternalRequest;

@Service
public class WeatherService {
	
	@Autowired
	private ExternalRequest externalRequest;
	
	@Autowired
	private WeatherRepository weatherRepository;
		
	@Async
	public void saveWeather(Long customerId, Long woeid) {
		WeatherModel weatherModel = externalRequest.getWeather(woeid);
		
		WeatherEntity weather = new WeatherEntity();
		weather.setCustomerId(customerId);
		weather.setMinTemp(weatherModel.getMinTemp());
		weather.setMaxTemp(weatherModel.getMaxTemp());
		
		weatherRepository.save(weather);
	}
}
