package com.mytest.billapp.model;

import java.io.Serializable;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "PURCHASE_ITEM")
@EntityListeners(AuditingEntityListener.class)
public class PurchaseItem  extends BEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "PURCHASE_ID")
	private Long purchaseId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_TYPE_ID")
	private Product product;
	
	@Column(name="PRODUCT_TYPE_TEXT")
	private String productTypeText;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="SIZE")
	private String size;
	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getProductTypeText() {
		return productTypeText;
	}

	public void setProductTypeText(String productTypeText) {
		this.productTypeText = productTypeText;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
}
