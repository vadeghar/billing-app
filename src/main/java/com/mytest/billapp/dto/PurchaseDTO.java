package com.mytest.billapp.dto;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDTO {
	
	private Long id;
	private Long vendorId;
	private String vendorName;
	private String vendorGst;
	private String vendorCity;
	private String vendorMobile;
	
	private String billDate;
	private String billNo;
	private PurchaseItemDTO purchaseItemDTO;
	private String entryDate;
	private Double billTotal;
	private String discountType;
	private Double discount;
	private Double discountedAmount;
	private Double netTotal;
	
	
	List<PurchaseItemDTO> purchaseItems = new ArrayList<>();
	List<PurchaseItemDTO> deletedPurchaseItems = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public Double getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(Double billTotal) {
		this.billTotal = billTotal;
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

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public List<PurchaseItemDTO> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(List<PurchaseItemDTO> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorGst() {
		return vendorGst;
	}

	public void setVendorGst(String vendorGst) {
		this.vendorGst = vendorGst;
	}

	public String getVendorCity() {
		return vendorCity;
	}

	public void setVendorCity(String vendorCity) {
		this.vendorCity = vendorCity;
	}

	public String getVendorMobile() {
		return vendorMobile;
	}

	public void setVendorMobile(String vendorMobile) {
		this.vendorMobile = vendorMobile;
	}

	public PurchaseItemDTO getPurchaseItemDTO() {
		return purchaseItemDTO;
	}

	public void setPurchaseItemDTO(PurchaseItemDTO purchaseItemDTO) {
		this.purchaseItemDTO = purchaseItemDTO;
	}

	public Double getDiscountedAmount() {
		return discountedAmount;
	}

	public void setDiscountedAmount(Double discountedAmount) {
		this.discountedAmount = discountedAmount;
	}

	public List<PurchaseItemDTO> getDeletedPurchaseItems() {
		return deletedPurchaseItems;
	}

	public void setDeletedPurchaseItems(List<PurchaseItemDTO> deletedPurchaseItems) {
		this.deletedPurchaseItems = deletedPurchaseItems;
	}
	
}
