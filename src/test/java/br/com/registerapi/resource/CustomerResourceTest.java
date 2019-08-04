package br.com.registerapi.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerResourceTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private CustomerResource customerResource;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerResource)
				.build();
	}
	
	@Test
	public void testCreateCustomer() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customer")
		)
			.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testUpdateCustomer() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customer")
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetCustomerById() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customer/{id}")
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetAllCustomers() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customers")
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDeleteCustomerById() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customer/{id}")
		)
			.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
