package br.com.registerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.registerapi.entity.GeolocationEntity;
import br.com.registerapi.entity.WoeidEntity;
import br.com.registerapi.model.WoeidModel;
import br.com.registerapi.model.WoeidWrapper;
import br.com.registerapi.repository.GeolocationRepository;
import br.com.registerapi.repository.WeatherRepository;
import br.com.registerapi.repository.WoeidRepository;
import br.com.registerapi.utils.ExternalRequest;
import ma.glasnost.orika.MapperFacade;

@Service
public class WoeidService {
	
	@Autowired
	private MapperFacade modelMapper;
	
	@Autowired
	private ExternalRequest externalRequest;
	
	@Autowired
	private WoeidRepository woeidRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	@Async
	public void saveWoeidAndWeather(Long customerId, String latitude, String longitude) {
		WoeidWrapper woeidWrapper = externalRequest.getWoeid(latitude, longitude);
		woeidWrapper.getList().setCustomerId(customerId);
		
		WoeidEntity woeid = new WoeidEntity();
		woeid = modelMapper.map(woeidWrapper.getList(), WoeidEntity.class);
		
		woeidRepository.save(woeid);
		
		weatherService.saveWeather(customerId, woeid.getWoeid());
	}

}
