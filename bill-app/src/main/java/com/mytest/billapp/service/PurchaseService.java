package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.model.PurchaseItem;

public interface PurchaseService {
	
	public PurchaseDTO saveOrUpatePurchase(PurchaseDTO entity);
	public List<Purchase> saveAll(List<Purchase> entities);
	public Purchase saveAndFlush(Purchase entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Purchase entity);
	public void deleteAll(List<Purchase> entities);
	public void deleteInBatch(List<Purchase> entities);
	public void deleteAll();
	public PurchaseDTO getOne(Long id);
	public PurchaseDTO findById(Long id);
	public boolean existsById(Long id);
	public List<PurchaseDTO> findAll();
	public List<Purchase> findAll(Sort sort);
	public long count();
	public void deletePurchaseItems(List<PurchaseItemDTO> deletedPurchaseItems);
	
	public void savePurchaseItemAndStock(PurchaseItemDTO purchaseItemDTO, Long purchaseId);
	public void updatePurchaseTotals(PurchaseDTO entity);
	public PurchaseItemDTO convertModelToView(PurchaseItem pi) ;
	public void deletePurchaseItemUpdateStock(PurchaseItem purchaseItem);

}
