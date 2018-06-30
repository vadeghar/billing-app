package com.mytest.billapp.view;

public class InvoiceItemView {
	
	private Integer srNo;
	private String desc;
	private String rate;
	private String quantity;
	private String totalItemCost;
	
	public Integer getSrNo() {
		return srNo;
	}
	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTotalItemCost() {
		return totalItemCost;
	}
	public void setTotalItemCost(String totalItemCost) {
		this.totalItemCost = totalItemCost;
	}
	
}
