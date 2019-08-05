package br.com.registerapi.resource;

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
    @ApiOperation(value = "Customer", nickname = "customer", notes = "Create a customer.", tags={"customer"})
        @ApiResponses(value = { 
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "invalid input, object invalid") })
    @RequestMapping(value = "/v1/customer",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<CustomerModel> createCustomer(@Valid @RequestBody CustomerModel customerModel);
	
	@CrossOrigin
    @ApiOperation(value = "Customer", nickname = "customer", notes = "Update a customer.", tags={"customer"})
        @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "invalid input, object invalid") })
	@RequestMapping(value = "/v1/customer/{id}",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.PUT)
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerModel customerModel);
}
