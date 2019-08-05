package br.com.registerapi.resource;

import java.util.ArrayList;
import java.util.List;

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
		
		return ResponseEntity.ok().body(null);
	}
	
	@Override
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable(value = "id") Long id) {
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Floriano Lopes", 35);
		return ResponseEntity.ok().body(customerModel);
	}
	
	@Override
	public ResponseEntity<List<CustomerModel>> getAllCustomers() {
		List<CustomerModel> listCustomers = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Floriano Lopes", 35);
		listCustomers.add(customerModel);
		return ResponseEntity.ok().body(listCustomers);
	}
	
	@Override
	public ResponseEntity<CustomerModel> deleteCustomerById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
