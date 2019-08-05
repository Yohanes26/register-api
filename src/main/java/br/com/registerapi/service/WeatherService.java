package br.com.registerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.registerapi.entity.WeatherEntity;
import br.com.registerapi.entity.WoeidEntity;
import br.com.registerapi.model.WeatherWrapper;
import br.com.registerapi.repository.WeatherRepository;
import br.com.registerapi.repository.WoeidRepository;
import br.com.registerapi.utils.ExternalRequest;
import ma.glasnost.orika.MapperFacade;

@Service
public class WeatherService {
	
	@Autowired
	private MapperFacade modelMapper;
	
	@Autowired
	private ExternalRequest externalRequest;
	
	@Autowired
	private WeatherRepository weatherRepository;
		
	@Async
	public void saveWeather(Long customerId, Long woeid) {
		WeatherWrapper weatherWrapper = externalRequest.getWeather(woeid);
		weatherWrapper.getConsolidatedWeather().get(0).setCustomerId(customerId);
		
		WeatherEntity weather = new WeatherEntity();
		weather = modelMapper.map(weatherWrapper.getConsolidatedWeather().get(0), WeatherEntity.class);
		
		weatherRepository.save(weather);
	}
}
