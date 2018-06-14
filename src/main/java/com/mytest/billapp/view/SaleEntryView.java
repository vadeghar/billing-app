package com.mytest.billapp.view;

public class SaleEntryView {
	
	private String productDescription;
	private Double rate;
	private Integer quantity;
	private Double total;
	private Long productItemId;
	private String itemCode;
	private Double invoiceTotal;
	private String discountType;
	private Double discount;
	private Double netTotal;
	
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Long getProductItemId() {
		return productItemId;
	}
	public void setProductItemId(Long productItemId) {
		this.productItemId = productItemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Double getInvoiceTotal() {
		return invoiceTotal;
	}
	public void setInvoiceTotal(Double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getNetTotal() {
		return netTotal;
	}
	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}
	@Override
	public String toString() {
		return "[productDescription=" + productDescription + ", rate=" + rate + ", quantity=" + quantity
				+ ", total=" + total + ", productItemId=" + productItemId + ", itemCode=" + itemCode + ", invoiceTotal="
				+ invoiceTotal + ", discountType=" + discountType + ", discount=" + discount + ", netTotal=" + netTotal
				+ "]";
	}
	
}
