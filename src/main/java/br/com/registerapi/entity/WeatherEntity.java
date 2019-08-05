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
@Table(schema = "customers", name = "weather",
indexes = {
        @Index(columnList = "id")
})
public class WeatherEntity {

    /**
     * Id da tabela weather (auto gerado)
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "weatherGenerator")
    @SequenceGenerator(name = "weatherGenerator", schema = "customers", sequenceName = "weather_seq", allocationSize = 1)
    private Long id;
    
    @Column(name = "customer_id")
    private Long customerId;
    
	@Column(name = "max_temp")
	private float maxTemp;
	
	@Column(name = "min_temp")
	private float minTemp;
	
	@Column(name = "applicable_date")
	private Timestamp applicableDate;
    
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

	public float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
	}

	public float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}

	public Timestamp getApplicableDate() {
		return applicableDate;
	}

	public void setApplicableDate(Timestamp applicableDate) {
		this.applicableDate = applicableDate;
	}

}
