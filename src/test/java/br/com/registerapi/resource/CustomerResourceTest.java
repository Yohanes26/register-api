package br.com.registerapi.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import br.com.registerapi.model.CustomerModel;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerResourceTest {
	
	Gson gson = new Gson();
	
	private MockMvc mockMvc;
	
	@Mock
	private CustomerResource customerResource;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerResource)
				.build();
	}
	
	@Test
	public void testCreateCustomer() throws Exception {
		
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Floriano Mendes", 35);
		
		mockMvc.perform(
				post("/v1/customer")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(gson.toJson(customerModel))
					.accept(MediaType.APPLICATION_JSON_UTF8)
					
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testUpdateCustomer() throws Exception {
		
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel("Floriano Lopes", 35);
		
		mockMvc.perform(
				put("/v1/customer/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(gson.toJson(customerModel))
				.accept(MediaType.APPLICATION_JSON_UTF8)
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetCustomerById() throws Exception {
		mockMvc.perform(
				get("/v1/customer/1")
					.accept(MediaType.APPLICATION_JSON_UTF8)
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetAllCustomers() throws Exception {
		mockMvc.perform(
				get("/v1/customers")
					.accept(MediaType.APPLICATION_JSON_UTF8)
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDeleteCustomerById() throws Exception {
		mockMvc.perform(
				delete("/v1/customer/{id}", 1)
		)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
