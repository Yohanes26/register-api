package br.com.registerapi.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerModel {

	@JsonIgnore
	@JsonProperty("id")
	private Long id;
    
	@NotNull
	@Size(min = 1, max = 255)
    @JsonProperty("name")
    private String name;
    
	@NotNull
	@Max(150)
    @JsonProperty("age")
	private Integer age;

	public void setCustomerModel (String name, Integer age) {
		this.name = name;
		this.age = age;
	} 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
