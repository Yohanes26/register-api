package br.com.registerapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNull;
import org.hibernate.service.spi.InjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerapi.config.ApiException;
import br.com.registerapi.entity.CustomerEntity;
import br.com.registerapi.model.CustomerModel;
import br.com.registerapi.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	
	@MockBean
    private CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
		CustomerEntity customer = new CustomerEntity();
		customer.setCustomerEntity("Paulo Alex", 45);
		customer.setId(1L);
	    Mockito.when(customerRepository.getOne(customer.getId()))
	      .thenReturn(customer);
	}
	
	@Test
    public void whenCreateCustomer_thenReturnCustomerWithId() { 
		// given
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Pedro Paulo", 70);
		
        // when
		CustomerModel customerNewModel = new CustomerModel();
		customerNewModel = customerService.createCustomer(customerModel, null);
     
        // then
		if (Objects.nonNull(customerNewModel)) {
			assertThat(customerNewModel.getId(), notNullValue());
		} else {
			assertThat(customerNewModel, notNullValue());
		}
		
    }
	
	@Test
    public void whenUpdateCustomer_thenReturnCustomerNewValue() { 
		// given
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Pedro Paulo", 70);
		
        // when
		CustomerModel customerNewModel = new CustomerModel();
		customerNewModel = customerService.updateCustomer(1L, customerModel);
     
        // then
		if (Objects.nonNull(customerNewModel)) {
			assertThat(customerNewModel.getId(), notNullValue());
		} else {
			assertThat(customerNewModel, notNullValue());
		}
		
		assertThat(customerNewModel.getName(), is(customerModel.getName()));
		assertThat(customerNewModel.getAge(), is(customerModel.getAge()));
		
    }
	
	@Test
    public void whenFindAll_thenReturnListCustomers() { 
		// given
		List<CustomerModel> listFound = new ArrayList<CustomerModel>();
		
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
        CustomerModel found = customerService.getCustomerById(customerId);
     
        // then
        if (Objects.nonNull(found)) {
        	assertThat(found.getName(), is("Paulo Alex"));
        } else {
        	assertThat(found, IsNull.notNullValue());
        }
        
    }
	
	@Test(expected = ApiException.class)
    public void whenFindByIdNull_thenReturnApiException() {
        // given
    	Long customerId = null;
     
        // when
        customerService.getCustomerById(customerId);
    }
	
	@Test(expected = ApiException.class)
    public void whenFindByIdNotIn_thenReturnApiException() {
        // given
    	Long customerId = 2L;
     
        // when
        customerService.getCustomerById(customerId);
    }
	
	@Test
    public void whenDeleteById_thenDeleteCustomer() { 
		// given
    	Long customerId = 1L;
    	
        // when
		customerService.getCustomerById(customerId);
     
        // then
        Mockito.verify(customerRepository, times(1))
        .deleteById(customerId);;
    }
	
	@Test(expected = ApiException.class)
    public void whenDeleteByIdNotIn_thenReturnApiException() {
        // given
    	Long customerId = 2L;
     
        // when
        customerService.deleteCustomerById(customerId);
    }
	
	@Test(expected = ApiException.class)
    public void whenDeleteByIdNull_thenReturnApiException() {
        // given
    	Long customerId = 2L;
     
        // when
        customerService.deleteCustomerById(customerId);
    }
}
