package com.mytest.billapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "PURCHASE_ITEM_YET_TO_SAVE")
@EntityListeners(AuditingEntityListener.class)
public class PurchaseItemYetToSave  extends BEntity implements Serializable {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="PURCHASE_ID")
	private Long purchaseId;
	
	@Column(name="PRODUCT_TYPE_ID")
	private Long productTypeId;
	
	@Column(name="PRODUCT_SIZE_ID")
	private Long productSizeId;
	
	@Column(name="SR_NO")
	private String srNo;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="PRICE_PER_PC")
	private Double pricePerPc;
	
	@Column(name="TOTAL_PRICE")
	private Double totalPrice;
	
	@Column(name="MARGIN_TYPE")
	private String marginType;
	
	@Column(name="MARGIN")
	private Double margin;
	
	@Column(name="ITEM_CODE")
	private String itemCode;
	
	@Column(name="CREATED_AT", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name="UPDATED_AT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Long getProductSizeId() {
		return productSizeId;
	}

	public void setProductSizeId(Long productSizeId) {
		this.productSizeId = productSizeId;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPricePerPc() {
		return pricePerPc;
	}

	public void setPricePerPc(Double pricePerPc) {
		this.pricePerPc = pricePerPc;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMarginType() {
		return marginType;
	}

	public void setMarginType(String marginType) {
		this.marginType = marginType;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
