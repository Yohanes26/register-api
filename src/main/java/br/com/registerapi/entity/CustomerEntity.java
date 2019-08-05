package br.com.registerapi.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(schema = "customers", name = "customers",
indexes = {
        @Index(columnList = "id")
})
public class CustomerEntity {

    /**
     * Id do cliente (auto gerado)
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "customerGenerator")
    @SequenceGenerator(name = "customerGenerator", schema = "customers", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private Integer age;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createAt;

    public void setCustomerEntity(String name, Integer age) {
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

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

}
