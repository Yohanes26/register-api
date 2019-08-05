package br.com.registerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.registerapi.entity.GeolocationEntity;
import br.com.registerapi.model.GeolocationWrapper;
import br.com.registerapi.repository.GeolocationRepository;
import br.com.registerapi.utils.ExternalRequest;
import ma.glasnost.orika.MapperFacade;

@Service
public class GeolocationService {

	@Autowired
	private MapperFacade modelMapper;
	
	@Autowired
	private ExternalRequest externalRequest;
	
	@Autowired
	private GeolocationRepository geolocationRepository;
	
	@Autowired
	private WoeidService woeidService;
	
	@Async
	public void saveGeolocationAndWoeid(Long customerId, String ip) {
		GeolocationWrapper geolocationWrapper = externalRequest.getGeolocation(ip);
		geolocationWrapper.getData().setCustomerId(customerId);
		
		GeolocationEntity geolocation = new GeolocationEntity();
		geolocation = modelMapper.map(geolocationWrapper.getData(), GeolocationEntity.class);
		
		geolocationRepository.save(geolocation);
		
		woeidService.saveWoeidAndWeather(customerId, geolocation.getLatitude(), geolocation.getLongitude());
	}
}
