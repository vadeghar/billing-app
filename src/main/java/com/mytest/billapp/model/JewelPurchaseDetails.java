package com.mytest.billapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mytest.billapp.utils.JsonDateDeSerializer;
import com.mytest.billapp.utils.JsonDateSerializer;

@Entity
@Table(name = "JEWEL_PURCHASE_DETAILS")
@EntityListeners(AuditingEntityListener.class)
public class JewelPurchaseDetails  extends BEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASE_ID")
    private JewelPurchase jewelPurchase;
	
	@Column(name="CATEGORY_ID")
	private Long categoryId;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="QUALITY")
	private Integer quality;
	
	@Column(name="TOTAL_WEIGHT")
	private BigDecimal totalWieght;
	
	@Column(name="AVG_WEIGHT")
	private BigDecimal avgWieght;
	
	@Column(name="RATE_CUT_AT")
	private BigDecimal rateCutAt;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize(using=JsonDateDeSerializer.class)
	@Column(name="RATE_CUT_DATE")
	private Date rateCutDate;
	
	@Column(name="TAX_RATE")
	private BigDecimal taxRate = new BigDecimal(0);
	
	@Column(name="MAKING_CHARGE_PER_PC")
	private BigDecimal makingChargePerPc = new BigDecimal(0);;
	
	@Column(name="WASTAGE_PER_PC")
	private BigDecimal wastagePerPc = new BigDecimal(0);;
	
	@Column(name="GENERATED_ITEM_CODE")
	private String generatedItemCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	@CreatedDate
	private Date entryDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFIED_DATE")
	@LastModifiedBy
	private Date modifiedDate;
	
	@Column(name="PURCHASE_TOTAL")
	private BigDecimal purchaseTotal;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JewelPurchase getJewelPurchase() {
		return jewelPurchase;
	}

	public void setJewelPurchase(JewelPurchase jewelPurchase) {
		this.jewelPurchase = jewelPurchase;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalWieght() {
		return totalWieght;
	}

	public void setTotalWieght(BigDecimal totalWieght) {
		this.totalWieght = totalWieght;
	}

	public BigDecimal getAvgWieght() {
		return avgWieght;
	}

	public void setAvgWieght(BigDecimal avgWieght) {
		this.avgWieght = avgWieght;
	}

	public BigDecimal getRateCutAt() {
		return rateCutAt;
	}

	public void setRateCutAt(BigDecimal rateCutAt) {
		this.rateCutAt = rateCutAt;
	}

	public Date getRateCutDate() {
		return rateCutDate;
	}

	public void setRateCutDate(Date rateCutDate) {
		this.rateCutDate = rateCutDate;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getMakingChargePerPc() {
		return makingChargePerPc;
	}

	public void setMakingChargePerPc(BigDecimal makingChargePerPc) {
		this.makingChargePerPc = makingChargePerPc;
	}

	public BigDecimal getWastagePerPc() {
		return wastagePerPc;
	}

	public void setWastagePerPc(BigDecimal wastagePerPc) {
		this.wastagePerPc = wastagePerPc;
	}

	public String getGeneratedItemCode() {
		return generatedItemCode;
	}

	public void setGeneratedItemCode(String generatedItemCode) {
		this.generatedItemCode = generatedItemCode;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public BigDecimal getPurchaseTotal() {
		return purchaseTotal;
	}

	public void setPurchaseTotal(BigDecimal purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}
	
}
