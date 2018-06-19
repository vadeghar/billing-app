package com.mytest.billapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Sale;
import com.mytest.billapp.model.SaleItems;
import com.mytest.billapp.model.Stock;
import com.mytest.billapp.repsitory.SalesItemsRepository;
import com.mytest.billapp.repsitory.SalesRepository;
import com.mytest.billapp.repsitory.StockRepository;
import com.mytest.billapp.service.SalesService;
import com.mytest.billapp.utils.AppUtils;
import com.mytest.billapp.view.SaleEntryView;

@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	SalesRepository salesRepository;
	
	@Autowired
	SalesItemsRepository salesItemsRepository; 
	
	@Autowired
	StockRepository stockRepository;

	@Override
	public boolean saveSales(List<SaleEntryView> saleEntryViewList) {
		
		if(CollectionUtils.isEmpty(saleEntryViewList)) return false;
		int i = 1;
		List<Sale> existingSales = salesRepository.findByInvoiceDate(new java.sql.Date(new Date().getTime()));
		if(CollectionUtils.isEmpty(existingSales))
			existingSales = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yyyyMMdd = sdf.format(new Date());
		Sale savedSale = null;
		for(SaleEntryView saleEntry : saleEntryViewList ) {
			Stock stock = stockRepository.findByItemCode(saleEntry.getItemCode());
			Integer existingQty = stock.getQuantity();
			if(existingQty < saleEntry.getQuantity()) {
				// Requested quantity is more than existing quantity 
				return false;
			}
			if(saleEntry.getSaleId() == null || saleEntry.getSaleId().intValue() == 0) {
				try {
					if(i ==1) {
						Sale sale = new Sale();
						sale.setInvoiceDate(new Date());
						sale.setDiscount(saleEntry.getDiscount());
						sale.setDiscountedAmount(AppUtils.getDiscountedValue(saleEntry.getInvoiceTotal(), saleEntry.getDiscount(), saleEntry.getDiscountType()));
						sale.setDiscountType(saleEntry.getDiscountType());
						sale.setInvoiceNo(yyyyMMdd+existingSales.size());
						sale.setInvoiceTotal(saleEntry.getInvoiceTotal());
						sale.setNetTotal(saleEntry.getNetTotal());
						sale.setEntryDate(new Date());
						savedSale = salesRepository.save(sale);
					}
					SaleItems saleItem = new SaleItems();
					saleItem.setItemCode(saleEntry.getItemCode());
					saleItem.setItemQty(saleEntry.getQuantity());
					saleItem.setItemDesc(saleEntry.getProductDescription());
					saleItem.setItemRate(saleEntry.getRate());
					saleItem.setItemTotal(saleEntry.getQuantity() * saleEntry.getRate());
					saleItem.setSaleId(savedSale.getId());
					salesItemsRepository.save(saleItem);
					if(stock != null) {
						stock.setQuantity(existingQty - saleEntry.getQuantity());
						stockRepository.save(stock);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			i++;
		}
		
		return false;
	}

}
