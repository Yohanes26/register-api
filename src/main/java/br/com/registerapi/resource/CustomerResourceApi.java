package br.com.registerapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.registerapi.model.CustomerModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "v1", description = "the v1 API")
public interface CustomerResourceApi {

	@CrossOrigin
    @ApiOperation(value = "createCustomer", nickname = "createCustomer", notes = "Create a customer.", tags={"customer"})
        @ApiResponses(value = { 
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "invalid input, object invalid") })
    @RequestMapping(value = "/v1/customer",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<CustomerModel> createCustomer(@Valid @RequestBody CustomerModel customerModel, HttpServletRequest request);
	
	@CrossOrigin
    @ApiOperation(value = "UpdateCustomer", nickname = "UpdateCustomer", notes = "Update a customer.", tags={"customer"})
        @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "invalid input, object invalid") })
	@RequestMapping(value = "/v1/customer/{id}",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.PUT)
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerModel customerModel);
	
	@CrossOrigin
    @ApiOperation(value = "getCustomer", nickname = "getCustomer", notes = "Get a customer by id.", tags={"customer"})
        @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "invalid input, object invalid") })
	@RequestMapping(value = "/v1/customer/{id}",
    produces = {"application/json"},
    method = RequestMethod.GET)
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable(value = "id") Long id);
	
	@CrossOrigin
    @ApiOperation(value = "getAllCustomers", nickname = "getAllCustomers", notes = "Get all customers.", tags={"customer"})
        @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "invalid input, object invalid") })
	@RequestMapping(value = "/v1/customers",
    produces = {"application/json"},
    method = RequestMethod.GET)
	public ResponseEntity<List<CustomerModel>> getAllCustomers();
	
	@CrossOrigin
    @ApiOperation(value = "deleteCustomer", nickname = "deleteCustomer", notes = "Delete customer by id.", tags={"customer"})
        @ApiResponses(value = { 
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 400, message = "invalid input, object invalid") })
	@RequestMapping(value = "/v1/customer/{id}",
    method = RequestMethod.DELETE)
	public ResponseEntity<CustomerModel> deleteCustomerById(@PathVariable(value = "id") Long id);
}
