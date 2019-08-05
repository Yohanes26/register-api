package br.com.registerapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.registerapi.config.ApiException;
import br.com.registerapi.entity.CustomerEntity;
import br.com.registerapi.model.CustomerModel;
import br.com.registerapi.repository.CustomerRepository;
import ma.glasnost.orika.MapperFacade;

@Service
public class CustomerService {

	private MapperFacade map;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public CustomerModel createCustomer(CustomerModel customerModel, String ip) {
		
		if (!Objects.nonNull(customerModel)) {
			throw new ApiException("Model não pode ser nula", 400);
		}
		
		if (!Objects.nonNull(customerModel.getName())) {
			throw new ApiException("Atributo 'name' não pode ser nulo", 400);
		}
		
		CustomerEntity customerEntity = map.map(customerModel, CustomerEntity.class);
		try {
			customerEntity = customerRepository.save(customerEntity);
		} catch (Exception ex) {
			throw new ApiException("Algum erro aconteceu ao salvar as informações", 500, ex);
		} 
		
		
		return map.map(customerEntity, CustomerModel.class);
	}
	
	@Transactional
	public CustomerModel updateCustomer(Long id, CustomerModel customerModel) {
		
		if (!Objects.nonNull(customerModel)) {
			throw new ApiException("Model não pode ser nula", 400);
		}
		
		if (!Objects.nonNull(customerModel.getName())) {
			throw new ApiException("Atributo 'name' não pode ser nulo", 400);
		}
		
		CustomerEntity customerEntity = customerRepository.getOne(id);
		
		if (!Objects.nonNull(customerEntity)) {
			String msg = "Dados não encontrados para o ID: {}";
			throw new ApiException(String.format(msg, id), 404);
		}
		
		fillEntity(customerEntity, customerModel);
		
		try {
			customerEntity = customerRepository.save(customerEntity);
		} catch (Exception ex) {
			throw new ApiException("Algum erro aconteceu ao salvar as informações", 500, ex);
		} 
		
		
		return map.map(customerEntity, CustomerModel.class);
	}
	
	@Transactional
	public CustomerModel getCustomerById(Long id) {
		
		if (id == null) {
			throw new ApiException("Id não pode ser nulo para pesquisa", 400);
		}
		
		CustomerEntity customerEntity = customerRepository.getOne(id);
		
		if (!Objects.nonNull(customerEntity)) {
			String msg = "Dados não encontrados para o ID: {}";
			throw new ApiException(String.format(msg, id), 404);
		}	
		
		return map.map(customerEntity, CustomerModel.class);
	}
	
	@Transactional
	public List<CustomerModel> getAllCustomers() {

		List<CustomerEntity> listCustomerEntity = new ArrayList<CustomerEntity>();
		listCustomerEntity = customerRepository.findAll();
		
		List<CustomerModel> listCustomerModel = new ArrayList<CustomerModel>();
		
		listCustomerEntity.forEach(customer -> {
			listCustomerModel.add(map.map(customer, CustomerModel.class));
		});
		
		return listCustomerModel;
	}
	
	@Transactional
	public void deleteCustomerById(Long id) {
		
		if (id == null) {
			throw new ApiException("Id não pode ser nulo", 400);
		}
		
		CustomerEntity customerEntity = customerRepository.getOne(id);
		
		if (!Objects.nonNull(customerEntity)) {
			String msg = "Dados não encontrados para o ID: {}";
			throw new ApiException(String.format(msg, id), 404);
		}	
		
		customerRepository.delete(customerEntity);
	}
	
	private void fillEntity(CustomerEntity customerEntity, CustomerModel customerModel) {
		customerEntity.setCustomerEntity(customerModel.getName(), customerModel.getAge());
	}
}
