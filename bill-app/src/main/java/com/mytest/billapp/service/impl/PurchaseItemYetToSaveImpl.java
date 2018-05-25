package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.PurchaseItemYetToSave;
import com.mytest.billapp.repsitory.PurchaseItemYetToSaveRepository;
import com.mytest.billapp.service.PurchaseItemYetToSaveService;

@Service
public class PurchaseItemYetToSaveImpl implements PurchaseItemYetToSaveService {
	
	@Autowired
	PurchaseItemYetToSaveRepository purchaseItemYetToSaveRepository; 
	
	public PurchaseItemYetToSave save(PurchaseItemYetToSave entity) {
		return purchaseItemYetToSaveRepository.save(entity);
	}
	
	public List<PurchaseItemYetToSave> save(List<PurchaseItemYetToSave> entities) {
		return purchaseItemYetToSaveRepository.save(entities);
	}
	
	public void flush() {
		purchaseItemYetToSaveRepository.flush();
	}
	
	public void deleteById(Long id) {
		purchaseItemYetToSaveRepository.delete(id);
	}
	
	public void delete(PurchaseItemYetToSave entity) {
		purchaseItemYetToSaveRepository.delete(entity);
	}
	
	public void deleteAll(List<PurchaseItemYetToSave> entities) {
		purchaseItemYetToSaveRepository.delete(entities);
	}
	
	public void deleteInBatch(List<PurchaseItemYetToSave> entities) {
		purchaseItemYetToSaveRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		purchaseItemYetToSaveRepository.deleteAll();
	}
	
	public PurchaseItemYetToSave getOne(Long id) {
		return purchaseItemYetToSaveRepository.getOne(id);
	}
	public PurchaseItemYetToSave findById(Long id) {
		return purchaseItemYetToSaveRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return purchaseItemYetToSaveRepository.exists(id);
	}
	
	public List<PurchaseItemYetToSave> findAll() {
		return purchaseItemYetToSaveRepository.findAll();
	}
	
	public List<PurchaseItemYetToSave> findAll(Sort sort) {
		return purchaseItemYetToSaveRepository.findAll(sort);
	}
	
	public long count() {
		return purchaseItemYetToSaveRepository.count();
	}
	
	
	
	public PurchaseItemYetToSave saveAndFlush(PurchaseItemYetToSave entity) {
		return purchaseItemYetToSaveRepository.saveAndFlush(entity);
	}

	@Override
	public List<PurchaseItemYetToSave> saveAll(List<PurchaseItemYetToSave> entities) {
		return purchaseItemYetToSaveRepository.save(entities);
	}
	
}
