package br.com.registerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.registerapi.entity.WoeidEntity;
import br.com.registerapi.repository.WoeidRepository;
import br.com.registerapi.utils.ExternalRequest;

@Service
public class WoeidService {
	
	@Autowired
	private ExternalRequest externalRequest;
	
	@Autowired
	private WoeidRepository woeidRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	@Async
	public void saveWoeidAndWeather(Long customerId, String latitude, String longitude) {
		Integer woeidLong = externalRequest.getWoeid(latitude, longitude);
		
		WoeidEntity woeid = new WoeidEntity();
		woeid.setWoeid(new Long(woeidLong));
		woeid.setCustomerId(customerId);
		
		woeidRepository.save(woeid);
		
		weatherService.saveWeather(customerId, woeid.getWoeid());
	}

}
