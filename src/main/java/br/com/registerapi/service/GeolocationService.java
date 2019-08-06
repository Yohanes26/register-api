package br.com.registerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.registerapi.entity.GeolocationEntity;
import br.com.registerapi.model.GeolocationWrapper;
import br.com.registerapi.repository.GeolocationRepository;
import br.com.registerapi.utils.ExternalRequest;

@Service
public class GeolocationService {
	
	@Autowired
	private ExternalRequest externalRequest;
	
	@Autowired
	private GeolocationRepository geolocationRepository;
	
	@Autowired
	private WoeidService woeidService;
	
	@Async
	public void saveGeolocationAndWoeid(Long customerId, String ip) {
		GeolocationWrapper geolocationWrapper = externalRequest.getGeolocation(ip);
		
		GeolocationEntity geolocation = new GeolocationEntity();
		geolocation.setCustomerId(customerId);
		geolocation.setIpv4(geolocationWrapper.getData().getIpv4());
		geolocation.setLatitude(geolocationWrapper.getData().getLatitude());
		geolocation.setLongitude(geolocationWrapper.getData().getLongitude());
		
		geolocationRepository.save(geolocation);
		
		woeidService.saveWoeidAndWeather(customerId, geolocation.getLatitude(), geolocation.getLongitude());
	}
}
