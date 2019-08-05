package br.com.registerapi.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.registerapi.model.CustomerModel;
import br.com.registerapi.service.CustomerService;

@RestController
public class CustomerResource implements CustomerResourceApi{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public ResponseEntity<CustomerModel> createCustomer(@Valid @RequestBody CustomerModel customerModel, HttpServletRequest request) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerModel, request.getRemoteAddr()));
	}
	
	@Override
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerModel customerModel) {
		
		return ResponseEntity.ok().body(customerService.updateCustomer(id, customerModel));
	}
	
	@Override
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable(value = "id") Long id) {
		
		return ResponseEntity.ok().body(customerService.getCustomerById(id));
	}
	
	@Override
	public ResponseEntity<List<CustomerModel>> getAllCustomers() {
		
		return ResponseEntity.ok().body(customerService.getAllCustomers());
	}
	
	@Override
	public ResponseEntity<CustomerModel> deleteCustomerById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customerService.deleteCustomerById(id));
	}
}
