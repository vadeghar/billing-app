package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.PurchaseItem;

public interface PurchaseItemService {
	
	
	public List<PurchaseItem> findByPurchaseId(Long purchaseId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	public PurchaseItemDTO save(PurchaseItemDTO entity);
	public List<PurchaseItem> saveAll(List<PurchaseItem> entities);
	public PurchaseItem saveAndFlush(PurchaseItem entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(PurchaseItem entity);
	public void deleteAll(List<PurchaseItem> entities);
	public void deleteInBatch(List<PurchaseItem> entities);
	public void deleteAll();
	public PurchaseItemDTO getOne(Long id);
	public PurchaseItemDTO findById(Long id);
	public boolean existsById(Long id);
	public List<PurchaseItemDTO> findAll();
	public List<PurchaseItem> findAll(Sort sort);
	public long count();

}
