package br.com.registerapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(schema = "customers", name = "woeid",
indexes = {
        @Index(columnList = "id")
})
public class WoeidEntity {

    /**
     * Id do woeid (auto gerado)
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "woeidGenerator")
    @SequenceGenerator(name = "woeidGenerator", schema = "customers", sequenceName = "woeid_seq", allocationSize = 1)
    private Long id;
    
    @Column(name = "customer_id")
    private Long customerId;

	@Column(name = "woeid")
	private Long woeid;

    @CreationTimestamp
    @Column(name = "created_at", updatable=false)
    private Date createAt;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getWoeid() {
		return woeid;
	}

	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}

}
