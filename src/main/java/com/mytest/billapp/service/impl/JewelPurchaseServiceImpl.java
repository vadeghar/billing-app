package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.JewelPurchase;
import com.mytest.billapp.repsitory.JewelPurchaseRepository;
import com.mytest.billapp.service.JewelPurchaseService;

@Service
public class JewelPurchaseServiceImpl implements JewelPurchaseService {
	
	@Autowired
	JewelPurchaseRepository jewelPurchaseRepository; 
	
	public JewelPurchase save(JewelPurchase entity) {
		return jewelPurchaseRepository.save(entity);
	}
	
	public List<JewelPurchase> saveAll(List<JewelPurchase> entities) {
		return jewelPurchaseRepository.save(entities);
	}
	
	public void flush() {
		jewelPurchaseRepository.flush();
	}
	
	public void deleteById(Long id) {
		jewelPurchaseRepository.delete(id);
	}
	
	public void delete(JewelPurchase entity) {
		jewelPurchaseRepository.delete(entity);
	}
	
	public void deleteAll(List<JewelPurchase> entities) {
		jewelPurchaseRepository.delete(entities);
	}
	
	public void deleteInBatch(List<JewelPurchase> entities) {
		jewelPurchaseRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		jewelPurchaseRepository.deleteAll();
	}
	
	public JewelPurchase getOne(Long id) {
		return jewelPurchaseRepository.getOne(id);
	}
	public JewelPurchase findById(Long id) {
		return jewelPurchaseRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return jewelPurchaseRepository.exists(id);
	}
	
	public List<JewelPurchase> findAll() {
		return jewelPurchaseRepository.findAll();
	}
	
	public List<JewelPurchase> findAll(Sort sort) {
		return jewelPurchaseRepository.findAll(sort);
	}
	
	public long count() {
		return jewelPurchaseRepository.count();
	}
	
	
	
	public JewelPurchase saveAndFlush(JewelPurchase entity) {
		return jewelPurchaseRepository.saveAndFlush(entity);
	}
	
}
