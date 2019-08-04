package br.com.registerapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    public Long id;
}