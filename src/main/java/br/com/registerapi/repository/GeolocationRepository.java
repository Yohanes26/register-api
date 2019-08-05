package br.com.registerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.registerapi.entity.GeolocationEntity;

@Repository
public interface GeolocationRepository extends JpaRepository<GeolocationEntity, Long>{

}
