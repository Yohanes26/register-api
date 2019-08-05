package br.com.registerapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerapi.config.ApiException;
import br.com.registerapi.entity.CustomerEntity;
import br.com.registerapi.model.CustomerModel;
import br.com.registerapi.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
    private CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
		Optional<CustomerEntity> customer;
		customer.get().setCustomerEntity("Paulo Alex", 45);
		customer.get().setId(1L);
	    Mockito.when(customerRepository.findById(customer.get().getId()))
	      .thenReturn(customer);
	}
	
	@Test
    public void whenCreateCustomer_thenReturnCustomerWithId() { 
		// given
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Pedro Paulo", 70);
		
        // when
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity = customerService.create(customerModel, null);
     
        // then
		if (Objects.nonNull(customerEntity)) {
			assertThat(customerEntity.getId(), notNullValue());
		} else {
			assertThat(customerEntity, notNullValue());
		}
		
    }
	
	@Test
    public void whenUpdateCustomer_thenReturnCustomerNewValue() { 
		// given
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Pedro Paulo", 70);
		
        // when
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity = customerService.updateCustomer(1L, customerModel);
     
        // then
		if (Objects.nonNull(customerEntity)) {
			assertThat(customerEntity.getId(), notNullValue());
		} else {
			assertThat(customerEntity, notNullValue());
		}
		
		assertThat(customerEntity.getName(), is(customerModel.getName()));
		assertThat(customerEntity.getAge(), is(customerModel.getAge()));
		
    }
	
	@Test
    public void whenFindAll_thenReturnListCustomers() { 
		// given
		List<CustomerEntity> listFound = new ArrayList<CustomerEntity>();
		
        // when
        listFound = customerService.getAllCustomers();
     
        // then
        assertThat(listFound, not(IsEmptyCollection.empty()));
    }
	
	@Test
    public void whenFindById_thenReturnCustomer() {
        // given
    	Long customerId = 1L;
     
        // when
        Optional<CustomerEntity> found = customerService.getById(customerId);
     
        // then
        if (found.isPresent()) {
        	assertThat(found.get().getName(), is("Paulo Alex"));
        } else {
        	assertThat(found.get(), IsNull.notNullValue());
        }
        
    }
	
	@Test(expected = ApiException.class)
    public void whenFindByIdNull_thenReturnApiException() {
        // given
    	Long customerId = null;
     
        // when
        customerService.getById(customerId);
    }
	
	@Test(expected = ApiException.class)
    public void whenFindByIdNotIn_thenReturnApiException() {
        // given
    	Long customerId = 2L;
     
        // when
        customerService.getById(customerId);
    }
	
	@Test
    public void whenDeleteById_thenDeleteCustomer() { 
		// given
    	Long customerId = 1L;
    	
        // when
		customerService.deleteById(customerId);
     
        // then
        Mockito.verify(customerRepository, times(1))
        .deleteById(customerId);;
    }
	
	@Test(expected = ApiException.class)
    public void whenDeleteByIdNotIn_thenReturnApiException() {
        // given
    	Long customerId = 2L;
     
        // when
        customerService.deleteById(customerId);
    }
	
	@Test(expected = ApiException.class)
    public void whenDeleteByIdNull_thenReturnApiException() {
        // given
    	Long customerId = 2L;
     
        // when
        customerService.deleteById(customerId);
    }
}
