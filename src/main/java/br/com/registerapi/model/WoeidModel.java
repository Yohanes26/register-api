package br.com.registerapi.model;

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
@Table(schema = "customers", name = "woeid",
indexes = {
        @Index(columnList = "id")
})
public class WoeidModel {

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

	@Column(name = "distance")
	private Long distance;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "location_type")
	private String locationType;
	
	@Column(name = "woeid")
	private Long woeid;
	
	@Column(name = "latt_long")
	private String lattLong;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createAt;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Long getWoeid() {
		return woeid;
	}

	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}

	public String getLattLong() {
		return lattLong;
	}

	public void setLattLong(String lattLong) {
		this.lattLong = lattLong;
	}
}
