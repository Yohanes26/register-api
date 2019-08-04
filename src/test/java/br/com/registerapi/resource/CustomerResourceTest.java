package br.com.registerapi.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
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
					.accept(MediaType.APPLICATION_JSON_UTF8)
		)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(jsonPath("$.*", Matchers.hasSize(3)));
	}
	
	@Test
	public void testUpdateCustomer() {
		mockMvc.perform(
				MockMvcRequestBuilders.put("/v1/customer")
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetCustomerById() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customer/{id}")
					.accept(MediaType.APPLICATION_JSON_UTF8)
		)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.*", Matchers.hasSize(3)));
	}
	
	@Test
	public void testGetAllCustomers() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customers")
					.accept(MediaType.APPLICATION_JSON_UTF8)
		)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.*", Matchers.hasEntry(String.class, ArrayList.class)));
	}
	
	@Test
	public void testDeleteCustomerById() {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/customer/{id}")
		)
			.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
