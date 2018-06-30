package com.mytest.billapp.service;

import java.util.List;

import com.mytest.billapp.model.Sale;
import com.mytest.billapp.model.SaleItems;
import com.mytest.billapp.view.SaleEntryView;

public interface SalesService {
	
	public String saveSales( List<SaleEntryView> saleEntryViewList);
	
	public Sale findByInvoiceNo(String invoiceNo);
	
	public List<SaleItems> findBySaleId(Long saleId);

}
