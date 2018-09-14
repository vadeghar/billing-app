package com.mytest.billapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "JEWEL_PURCHASE")
@EntityListeners(AuditingEntityListener.class)
public class JewelPurchase  extends BEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="SUPPLIER_ID")
	private Long supplierId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;
	
	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount;
	
	@OneToMany(
	        mappedBy = "jewelPurchase", 
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	private List<JewelPurchaseDetails> jewelPurchaseDetails = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<JewelPurchaseDetails> getJewelPurchaseDetails() {
		return jewelPurchaseDetails;
	}

	public void setJewelPurchaseDetails(List<JewelPurchaseDetails> jewelPurchaseDetails) {
		this.jewelPurchaseDetails = jewelPurchaseDetails;
	}
	
}
