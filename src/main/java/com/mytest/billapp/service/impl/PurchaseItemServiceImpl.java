package com.mytest.billapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.PurchaseItem;
import com.mytest.billapp.repsitory.PurchaseItemRepository;
import com.mytest.billapp.service.PurchaseItemService;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	PurchaseItemRepository purchaseRepoistoryItem;
	
	public List<PurchaseItem> findByPurchaseId(Long purchaseId) {
		return purchaseRepoistoryItem.findByPurchaseId(purchaseId);
	}
	
	
	
	
	
	
	
	
	
	

	@Override
	public PurchaseItemDTO save(PurchaseItemDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PurchaseItem> saveAll(List<PurchaseItem> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseItem saveAndFlush(PurchaseItem entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PurchaseItem entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(List<PurchaseItem> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(List<PurchaseItem> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PurchaseItem getOne(Long id) {
		return purchaseRepoistoryItem.getOne(id);
	}

	@Override
	public PurchaseItemDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PurchaseItemDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PurchaseItem> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
