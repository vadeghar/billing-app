package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.repsitory.PurchaseRepository;
import com.mytest.billapp.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	PurchaseRepository purchaseRepository; 
	
	public Purchase save(Purchase entity) {
		return purchaseRepository.save(entity);
	}
	
	public List<Purchase> saveAll(List<Purchase> entities) {
		return purchaseRepository.saveAll(entities);
	}
	
	public void flush() {
		purchaseRepository.flush();
	}
	
	public void deleteById(Long id) {
		purchaseRepository.deleteById(id);
	}
	
	public void delete(Purchase entity) {
		purchaseRepository.delete(entity);
	}
	
	public void deleteAll(List<Purchase> entities) {
		purchaseRepository.deleteAll(entities);
	}
	
	public void deleteInBatch(List<Purchase> entities) {
		purchaseRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		purchaseRepository.deleteAll();
	}
	
	public Purchase getOne(Long id) {
		return purchaseRepository.getOne(id);
	}
	public Optional<Purchase> findById(Long id) {
		return purchaseRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return purchaseRepository.existsById(id);
	}
	
	public List<Purchase> findAll() {
		return purchaseRepository.findAll();
	}
	
	public List<Purchase> findAll(Sort sort) {
		return purchaseRepository.findAll(sort);
	}
	
	public long count() {
		return purchaseRepository.count();
	}
	
	
	
	public Purchase saveAndFlush(Purchase entity) {
		return purchaseRepository.saveAndFlush(entity);
	}
	
}
