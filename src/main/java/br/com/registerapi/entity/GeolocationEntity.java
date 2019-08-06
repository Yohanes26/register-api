package br.com.registerapi.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(schema = "customers", name = "geolocation",
indexes = {
        @Index(columnList = "id")
})
public class GeolocationEntity {

    /**
     * Id da Geolocalizacao (auto gerado)
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "geolocationGenerator")
    @SequenceGenerator(name = "geolocationGenerator", schema = "customers", sequenceName = "geolocation_seq", allocationSize = 1)
    private Long id;
    
    @Column(name = "customer_id")
    @NotNull
    private Long customerId;
    
	@Column(name = "ipv4")
	private String ipv4;
    				
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable=false)
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

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
