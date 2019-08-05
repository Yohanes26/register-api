package br.com.registerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.registerapi.entity.WoeidEntity;

@Repository
public interface WoeidRepository extends JpaRepository<WoeidEntity, Long>{

}
