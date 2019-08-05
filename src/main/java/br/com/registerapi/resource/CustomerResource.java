package br.com.registerapi.resource;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.registerapi.model.CustomerModel;

@RestController
public class CustomerResource implements CustomerResourceApi{

	@Override
	public ResponseEntity<CustomerModel> createCustomer(@Valid @RequestBody CustomerModel customerModel) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@Override
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerModel customerModel) {
		
		return null;
	}

}
