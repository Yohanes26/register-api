package br.com.registerapi.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerapi.entity.CustomerEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryIntegrationTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CustomerRepository customerRepository;
 
    
    @Test
    public void whenFindById_thenReturnCustomer() {
        // given
    	CustomerEntity customerEntity = new CustomerEntity();
    	customerEntity.setCustomerEntity("Floriano Mendes", 35);
    	customerEntity = entityManager.persist(customerEntity);
        entityManager.flush();
     
        // when
        Optional<CustomerEntity> found = customerRepository.findById(customerEntity.getId());
     
        // then
        assertThat(found.get().getName(), is(customerEntity.getName()));
    }
    
    @Test
    public void whenFindAll_thenReturnAllCustomers() {
        // given
    	CustomerEntity customerEntity = new CustomerEntity();
    	customerEntity.setCustomerEntity("Floriano Mendes", 35);
		entityManager.persist(customerEntity);
        entityManager.flush();
        
        customerEntity.setCustomerEntity("José Lopes", 30);
		entityManager.persist(customerEntity);
        entityManager.flush();
     
        // when
        List<CustomerEntity> foundList = customerRepository.findAll();
     
        // then
        assertThat(foundList, not(IsEmptyCollection.empty()));
    }
    
}
