package com.mytest.billapp.view;

import java.util.List;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.model.ProductItems;
import com.mytest.billapp.model.Vendor;

public class PurchaseView extends BaseView{
	
	
	private List<PurchaseDTO> purchaseList;
	private List<PurchaseItemDTO> purchaseItemList;
	private PurchaseDTO purchase;
	private PurchaseItemDTO purchaseItemDTO;
	private Long selectedPurchaseItemId;
	private List<Vendor> vendorList;
	private List<Product> productList;
	private List<ProductItems> productItemsList;
	
	public List<PurchaseDTO> getPurchaseList() {
		return purchaseList;
	}
	public void setPurchaseList(List<PurchaseDTO> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public List<PurchaseItemDTO> getPurchaseItemList() {
		return purchaseItemList;
	}
	public void setPurchaseItemList(List<PurchaseItemDTO> purchaseItemList) {
		this.purchaseItemList = purchaseItemList;
	}
	public PurchaseDTO getPurchase() {
		return purchase;
	}
	public void setPurchase(PurchaseDTO purchase) {
		this.purchase = purchase;
	}
	public PurchaseItemDTO getPurchaseItemDTO() {
		return purchaseItemDTO;
	}
	public void setPurchaseItemDTO(PurchaseItemDTO purchaseItemDTO) {
		this.purchaseItemDTO = purchaseItemDTO;
	}
	public Long getSelectedPurchaseItemId() {
		return selectedPurchaseItemId;
	}
	public void setSelectedPurchaseItemId(Long selectedPurchaseItemId) {
		this.selectedPurchaseItemId = selectedPurchaseItemId;
	}
	public List<Vendor> getVendorList() {
		return vendorList;
	}
	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public List<ProductItems> getProductItemsList() {
		return productItemsList;
	}
	public void setProductItemsList(List<ProductItems> productItemsList) {
		this.productItemsList = productItemsList;
	}
	
}
