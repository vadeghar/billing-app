package com.mytest.billapp.dto;

public class PurchaseItemDTO {
	
	
	public PurchaseItemDTO(){}
	
	
	public PurchaseItemDTO(PurchaseItemDTO dto) {
		this.id = dto.getId();
		this.srNo = dto.getSrNo();
		this.productId = dto.getProductId();
		this.size = dto.getSize();
		this.quantity = dto.getQuantity();
		this.pricePerUnit = dto.getPricePerUnit();
		this.total = dto.getTotal();
		this.marginType = dto.getMarginType();
		this.margin = dto.getMargin();
		this.salePrice = dto.getSalePrice();
		this.itemCode = dto.getItemCode();
	}
	private Long id;
	private String srNo;
	private Integer productId;
	/*private String productType;
	private String model;*/
	private String size;
	private Integer quantity;
	private Double pricePerUnit;
	private Double total;
	private String marginType;
	private Double margin;
	private Double salePrice;
	private String itemCode;
	
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
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	
	
	
	
	
}
