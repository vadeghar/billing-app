package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Brand;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.model.ProductItems;
import com.mytest.billapp.model.PurchaseItem;
import com.mytest.billapp.model.Stock;
import com.mytest.billapp.repsitory.BrandRepository;
import com.mytest.billapp.repsitory.ProductItemsRepository;
import com.mytest.billapp.repsitory.ProductRepository;
import com.mytest.billapp.repsitory.PurchaseItemRepository;
import com.mytest.billapp.repsitory.StockRepository;
import com.mytest.billapp.service.StockService;
import com.mytest.billapp.view.SaleEntryView;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockRepository stockRepository; 
	
	@Autowired
	ProductItemsRepository productItemsRepository; 
	
	@Autowired
	ProductRepository productRepository; 
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	public Stock save(Stock entity) {
		return stockRepository.save(entity);
	}
	
	public List<Stock> saveAll(List<Stock> entities) {
		return stockRepository.save(entities);
	}
	
	public void flush() {
		stockRepository.flush();
	}
	
	public void deleteById(Long id) {
		stockRepository.delete(id);
	}
	
	public void delete(Stock entity) {
		stockRepository.delete(entity);
	}
	
	public void deleteAll(List<Stock> entities) {
		stockRepository.delete(entities);
	}
	
	public void deleteInBatch(List<Stock> entities) {
		stockRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		stockRepository.deleteAll();
	}
	
	public Stock getOne(Long id) {
		return stockRepository.getOne(id);
	}
	public Stock findById(Long id) {
		return stockRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return stockRepository.exists(id);
	}
	
	public List<Stock> findAll() {
		return stockRepository.findAll();
	}
	
	public List<Stock> findAll(Sort sort) {
		return stockRepository.findAll(sort);
	}
	
	public long count() {
		return stockRepository.count();
	}
	
	
	
	public Stock saveAndFlush(Stock entity) {
		return stockRepository.saveAndFlush(entity);
	}

	@Override
	public void addToSrock(List<PurchaseItemDTO> purchaseItems ) {
		if(CollectionUtils.isEmpty(purchaseItems)) return;
		for(PurchaseItemDTO purchaseItemDTO : purchaseItems) {
			if(purchaseItemDTO.getItemCode() == null) continue;
			Stock stock = stockRepository.findByItemCode(purchaseItemDTO.getItemCode());
			if(stock == null) {
				stock = new Stock();
				stock.setItemCode(purchaseItemDTO.getItemCode());
				stock.setQuantity(purchaseItemDTO.getQuantity());
				stock.setSalePricePerPc(purchaseItemDTO.getSalePrice());
				stockRepository.save(stock);
			} else {
				int existingStock = stock.getQuantity();
				int updatedStock = purchaseItemDTO.getQuantity() + existingStock;
				stock.setQuantity(updatedStock);
				stockRepository.save(stock);
			}
		}
	}
	
	@Override
	public void removeFromSrock(List<PurchaseItemDTO> purchaseItems ) {
		if(CollectionUtils.isEmpty(purchaseItems)) return;
		for(PurchaseItemDTO purchaseItemDTO : purchaseItems) {
			if(purchaseItemDTO.getItemCode() == null) continue;
			Stock stock = stockRepository.findByItemCode(purchaseItemDTO.getItemCode());
			if(stock == null) {
				stock = new Stock();
				stock.setItemCode(purchaseItemDTO.getItemCode());
				stock.setQuantity(purchaseItemDTO.getQuantity());
				stock.setSalePricePerPc(purchaseItemDTO.getSalePrice());
			} else {
				Integer existingQty = stock.getQuantity();
				Integer updatedQty = existingQty - purchaseItemDTO.getQuantity();
				stock.setQuantity(updatedQty);
			}
			stockRepository.save(stock);
		}
	}

	@Override
	public SaleEntryView getStockEntryByItemCode(String itemCode) {
		Stock stock = stockRepository.findByItemCode(itemCode);
		SaleEntryView saleEntryView = new SaleEntryView();
		StringBuilder desc = new StringBuilder();
		if(stock != null) {
			saleEntryView.setItemCode(itemCode);
			saleEntryView.setRate(stock.getSalePricePerPc());
			saleEntryView.setQuantity(1);
			List<PurchaseItem> purchaseItem = purchaseItemRepository.findByItemCode(itemCode);
			ProductItems productItem = productItemsRepository.getOne(purchaseItem.get(0).getProductItemId());
			Product product = productRepository.getOne(productItem.getProductId());
			Brand brand = brandRepository.getOne(product.getBrandId());
			if(!brand.getName().equalsIgnoreCase("Other")) {
				desc.append(brand.getName()+" ");
			}
			desc.append(product.getName()+" ");
			desc.append(productItem.getName());
			saleEntryView.setProductDescription(desc.toString());
		}
		return saleEntryView;
	}
	
}
