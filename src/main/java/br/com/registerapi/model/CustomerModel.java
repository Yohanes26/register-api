package br.com.registerapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerModel {

	@JsonIgnore
	@JsonProperty("id")
	private Long id;
    
	@NotNull
	@Size(max = 255)
    @JsonProperty("name")
    private String name;
    
	@NotNull
	@Size(max = 3)
    @JsonProperty("age")
	private Integer age;

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
