package com.mytest.billapp.view;

import java.util.List;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;

public class PurchaseView extends BaseView{
	
	
	private List<PurchaseDTO> purchaseList;
	private List<PurchaseItemDTO> purchaseItemList;
	private PurchaseDTO purchase;
	private PurchaseItemDTO purchaseItemDTO;
	private Long selectedPurchaseItemId;
	
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
}
