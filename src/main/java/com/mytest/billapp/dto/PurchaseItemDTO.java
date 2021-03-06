package com.mytest.billapp.dto;

import com.mytest.billapp.utils.ProductSizeEnum;
import com.mytest.billapp.utils.ProductTypeEnum;

public class PurchaseItemDTO {
	
	
	public PurchaseItemDTO(){}
	
	
	public PurchaseItemDTO(PurchaseItemDTO dto) {
		this.id = dto.getId();
		this.srNo = dto.getSrNo();
		this.productId = dto.getProductId();
		this.quantity = dto.getQuantity();
		this.pricePerUnit = dto.getPricePerUnit();
		this.productItemId = dto.getProductId();
		this.total = dto.getTotal();
		this.marginType = dto.getMarginType();
		this.margin = dto.getMargin();
		this.salePrice = dto.getSalePrice();
		this.itemCode = dto.getItemCode();
		this.purchaseId = dto.getPurchaseId();
	}
	private Long id;
	private String srNo;
	private Long productId;
	private String productName;
	private Long productItemId;
	private String productItemName;
	private Integer quantity;
	private Double pricePerUnit;
	private Double total;
	private String marginType;
	private Double margin;
	private Double salePrice;
	private String itemCode;
	private Long purchaseId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
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
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	
	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Long getProductItemId() {
		return productItemId;
	}


	public void setProductItemId(Long productItemId) {
		this.productItemId = productItemId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductItemName() {
		return productItemName;
	}


	public void setProductItemName(String productItemName) {
		this.productItemName = productItemName;
	}


	public Long getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}


}
