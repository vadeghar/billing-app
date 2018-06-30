package com.mytest.billapp.view;

import java.util.List;

public class InvoicePrintView {
	private InvoiceHeaderView invoiceHeader;
	
	private String invoiceNumber;
	private String invoiceDate;
	private List<InvoiceItemView> invoiceItems;
	private String itemCount = "0";
	private String invoiceItemTotal;
	private String invoiceDiscount;
	private String invoiceNetAmount;
	
	public InvoiceHeaderView getInvoiceHeader() {
		return invoiceHeader;
	}
	public void setInvoiceHeader(InvoiceHeaderView invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public List<InvoiceItemView> getInvoiceItems() {
		return invoiceItems;
	}
	public void setInvoiceItems(List<InvoiceItemView> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	public String getInvoiceItemTotal() {
		return invoiceItemTotal;
	}
	public void setInvoiceItemTotal(String invoiceItemTotal) {
		this.invoiceItemTotal = invoiceItemTotal;
	}
	public String getInvoiceDiscount() {
		return invoiceDiscount;
	}
	public void setInvoiceDiscount(String invoiceDiscount) {
		this.invoiceDiscount = invoiceDiscount;
	}
	public String getInvoiceNetAmount() {
		return invoiceNetAmount;
	}
	public void setInvoiceNetAmount(String invoiceNetAmount) {
		this.invoiceNetAmount = invoiceNetAmount;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	
	

}
