package com.mytest.billapp.service;

import java.util.List;

import com.mytest.billapp.view.SaleEntryView;

public interface SalesService {
	
	public String saveSales( List<SaleEntryView> saleEntryViewList);

}
