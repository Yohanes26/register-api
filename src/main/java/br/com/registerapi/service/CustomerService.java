package br.com.registerapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.registerapi.config.ApiException;
import br.com.registerapi.entity.CustomerEntity;
import br.com.registerapi.model.CustomerModel;
import br.com.registerapi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GeolocationService geolocationService;
	
	@Transactional
	public CustomerModel createCustomer(CustomerModel customerModel, String ip) {
		
		if (!Objects.nonNull(customerModel)) {
			throw new ApiException("Model não pode ser nula", 400);
		}
		
		if (!Objects.nonNull(customerModel.getName())) {
			throw new ApiException("Atributo 'name' não pode ser nulo", 400);
		}
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerEntity(customerModel.getName(), customerModel.getAge());
		
		if (Objects.nonNull(customerModel.getId())) {
			customerEntity.setId(customerModel.getId());
		}
		
		try {
			customerEntity = customerRepository.save(customerEntity);
			if (customerEntity != null) {
				geolocationService.saveGeolocationAndWoeid(customerEntity.getId(), ip);
			}

		} catch (Exception ex) {
			throw new ApiException("Algum erro aconteceu ao salvar as informações", 500, ex);
		} 
		
		if (customerEntity != null) {
			customerModel = new CustomerModel();
			customerModel.setCustomerModel(customerEntity.getName(), customerEntity.getAge());
			customerModel.setId(customerEntity.getId());			
		}
		return customerModel;
	}
	
	@Transactional
	public CustomerModel updateCustomer(Long id, CustomerModel customerModel) {
		
		if (!Objects.nonNull(customerModel)) {
			throw new ApiException("Model não pode ser nula", 400);
		}
		
		if (!Objects.nonNull(customerModel.getName())) {
			throw new ApiException("Atributo 'name' não pode ser nulo", 400);
		}
		
		Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
		
		if (!Objects.nonNull(customerEntity)) {
			String msg = "Dados não encontrados para o ID: " + id;
			throw new ApiException(msg, 404);
		}
		
		fillEntity(customerEntity.get(), customerModel);		
		
		customerRepository.save(customerEntity.get());
		
		customerModel.setCustomerModel(customerEntity.get().getName(), customerEntity.get().getAge());
		customerModel.setId(customerEntity.get().getId());
		
		return customerModel;
	}
	
	@Transactional
	public CustomerModel getCustomerById(Long id) {
		
		if (id == null) {
			throw new ApiException("Id não pode ser nulo para pesquisa", 400);
		}
		
		Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
		
		if (!customerEntity.isPresent()) {
			String msg = "Dados não encontrados para o ID: " + id;
			throw new ApiException(msg, 404);
		}	
		
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel(customerEntity.get().getName(), customerEntity.get().getAge());
		customerModel.setId(customerEntity.get().getId());
		
		return customerModel;
	}
	
	@Transactional
	public List<CustomerModel> getAllCustomers() {

		List<CustomerEntity> listCustomerEntity = new ArrayList<CustomerEntity>();
		listCustomerEntity = customerRepository.findAll();
		
		List<CustomerModel> listCustomerModel = new ArrayList<CustomerModel>();
		
		listCustomerEntity.forEach(customer -> {
			CustomerModel customerModel = new CustomerModel();
			customerModel.setCustomerModel(customer.getName(), customer.getAge());
			customerModel.setId(customer.getId());
			listCustomerModel.add(customerModel);
		});
		
		return listCustomerModel;
	}
	
	@Transactional
	public void deleteCustomerById(Long id) {
		
		if (id == null) {
			throw new ApiException("Id não pode ser nulo", 400);
		}
		
		Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
		
		if (!customerEntity.isPresent()) {
			String msg = "Dados não encontrados para o ID: " + id;
			throw new ApiException(msg, 404);
		}	
		
		customerRepository.delete(customerEntity.get());
	}
	
	private void fillEntity(CustomerEntity customerEntity, CustomerModel customerModel) {
		customerEntity.setCustomerEntity(customerModel.getName(), customerModel.getAge());
	}
}
