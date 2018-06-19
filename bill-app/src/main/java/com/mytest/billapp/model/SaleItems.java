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
@Table(name = "SALE_ITEMS")
@EntityListeners(AuditingEntityListener.class)
public class SaleItems  extends BEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "SALE_ID")
	private Long saleId;
	
	@Column(name = "ITEM_CODE")
	private String itemCode;
	
	@Column(name="ITEM_DESC")
	private String itemDesc;
	
	@Column(name = "ITEM_RATE")
	private Double itemRate;
	
	
	@Column(name = "ITEM_QTY")
	private Integer itemQty;
	
	
	@Column(name = "item_total")
	private Double itemTotal;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ITEM_ADDED_TS")
	@CreatedDate
	private Date itemAddedTs;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ITEM_UPDATED_TS")
	@LastModifiedDate
	private Date itemUpdatedTs;
	
	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Double getItemRate() {
		return itemRate;
	}

	public void setItemRate(Double itemRate) {
		this.itemRate = itemRate;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	public Double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public Date getItemAddedTs() {
		return itemAddedTs;
	}

	public void setItemAddedTs(Date itemAddedTs) {
		this.itemAddedTs = itemAddedTs;
	}

	public Date getItemUpdatedTs() {
		return itemUpdatedTs;
	}

	public void setItemUpdatedTs(Date itemUpdatedTs) {
		this.itemUpdatedTs = itemUpdatedTs;
	}
	
	
}
