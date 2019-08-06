package br.com.registerapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerapi.RegisterApiApplication;
import br.com.registerapi.config.ApiException;
import br.com.registerapi.entity.CustomerEntity;
import br.com.registerapi.model.CustomerModel;
import br.com.registerapi.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	
	@Mock
    private CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
		CustomerEntity customer = new CustomerEntity();
		customer.setCustomerEntity("Jhon snow", 31);
		customer.setId(1L);
		
		ArrayList<CustomerEntity> listCustomer = new ArrayList<CustomerEntity>();
		listCustomer.add(customer);
		listCustomer.add(customer);
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		when(customerRepository.findAll()).thenReturn(listCustomer);
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerEntity("Pedro Paulo", 70);
		
		CustomerEntity newcustomerEntity = new CustomerEntity();
		customerEntity.setCustomerEntity("Pedro Paulo", 70);
		customerEntity.setId(35L);
		when(customerRepository.save(customerEntity)).thenReturn(newcustomerEntity);
	}
	
	@Test
    public void whenCreateCustomer_thenReturnCustomerWithId() { 
		// given
		CustomerEntity customer = new CustomerEntity();
		customer.setCustomerEntity("Pedro Paulo", 70);
		customer.setId(40L);
		when(customerRepository.save(customer)).thenReturn(customer);
		
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Pedro Paulo", 70);
		customerModel.setId(40L);
		
        // when
		CustomerModel customerNewModel = new CustomerModel();
		customerNewModel = customerService.createCustomer(customerModel, "0.0.0.1");
     
        // then
		if (Objects.nonNull(customerNewModel)) {
			assertThat(customerNewModel.getId(), notNullValue());
		} 
		
    }
	
	@Test
    public void whenUpdateCustomer_thenReturnCustomerNewValue() { 
		// given		
		CustomerEntity customer = new CustomerEntity();
		customer.setCustomerEntity("Jhon snow", 31);
		customer.setId(1L);
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
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
        	assertThat(found.getName(), is("Jhon snow"));
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
		customerService.deleteCustomerById(customerId);
		
		Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
     
        // then
        Mockito.verify(customerRepository, times(1))
        .delete(customerEntity.get());
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
    	Long customerId = null;
     
        // when
        customerService.deleteCustomerById(customerId);
    }
}
