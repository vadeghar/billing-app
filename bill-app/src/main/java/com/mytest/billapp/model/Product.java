package com.mytest.billapp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "PRODUCT")
@EntityListeners(AuditingEntityListener.class)
public class Product extends BEntity implements Serializable {
	
	public static final	String[] TEXT_FIELDS = {"brand.name", "name"};
	public static final	String[] NON_TEXT_FIELDS = {"id"};
	
	public Product() {}
	
	public Product(Long id, String name, Long brandId) {
		this.id = id;
		this.name=name;
		this.brandId = brandId;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/*@ManyToOne(optional=true, cascade=CascadeType.ALL)
	@JoinColumn(name = "BRAND_ID", foreignKey=@ForeignKey(name="BRAND_ID_FK"),  nullable = true)
	private Brand brand;*/
	@Column(name="BRAND_ID")
	private Long brandId;
	
    @Column
    private String name;
    
    @Transient
    public String brandName;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	/*public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}*/
	
	

	public String getName() {
		return name;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
