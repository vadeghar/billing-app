package com.mytest.billapp.view;

import java.util.List;

import com.mytest.billapp.model.Sale;

public class SaleView  extends BaseView {
	
	private Sale sale;
	private CustomerView cosutmerVIew;
	private List<SaleEntryView> saleEntryList;
	private SaleEntryView saleEntryVIew;
	
	
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public CustomerView getCosutmerVIew() {
		return cosutmerVIew;
	}
	public void setCosutmerVIew(CustomerView cosutmerVIew) {
		this.cosutmerVIew = cosutmerVIew;
	}
	public List<SaleEntryView> getSaleEntryList() {
		return saleEntryList;
	}
	public void setSaleEntryList(List<SaleEntryView> saleEntryList) {
		this.saleEntryList = saleEntryList;
	}
	public SaleEntryView getSaleEntryVIew() {
		return saleEntryVIew;
	}
	public void setSaleEntryVIew(SaleEntryView saleEntryVIew) {
		this.saleEntryVIew = saleEntryVIew;
	}
	
	

}
