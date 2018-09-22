package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.JewelPurchaseDetails;
import com.mytest.billapp.repsitory.JewelPurchaseDetailsRepository;
import com.mytest.billapp.service.JewelPurchaseDetailsService;

@Service
public class JewelPurchaseDetailsServiceImpl implements JewelPurchaseDetailsService {
	
	@Autowired
	JewelPurchaseDetailsRepository jewelPurchaseDetailsRepository; 
	
	public JewelPurchaseDetails save(JewelPurchaseDetails entity) {
		return jewelPurchaseDetailsRepository.save(entity);
	}
	
	public List<JewelPurchaseDetails> saveAll(List<JewelPurchaseDetails> entities) {
		return jewelPurchaseDetailsRepository.save(entities);
	}
	
	public void flush() {
		jewelPurchaseDetailsRepository.flush();
	}
	
	public void deleteById(Long id) {
		jewelPurchaseDetailsRepository.delete(id);
	}
	
	public void delete(JewelPurchaseDetails entity) {
		jewelPurchaseDetailsRepository.delete(entity);
	}
	
	public void deleteAll(List<JewelPurchaseDetails> entities) {
		jewelPurchaseDetailsRepository.delete(entities);
	}
	
	public void deleteInBatch(List<JewelPurchaseDetails> entities) {
		jewelPurchaseDetailsRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		jewelPurchaseDetailsRepository.deleteAll();
	}
	
	public JewelPurchaseDetails getOne(Long id) {
		return jewelPurchaseDetailsRepository.getOne(id);
	}
	public JewelPurchaseDetails findById(Long id) {
		return jewelPurchaseDetailsRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return jewelPurchaseDetailsRepository.exists(id);
	}
	
	public List<JewelPurchaseDetails> findAll() {
		return jewelPurchaseDetailsRepository.findAll();
	}
	
	public List<JewelPurchaseDetails> findAll(Sort sort) {
		return jewelPurchaseDetailsRepository.findAll(sort);
	}
	
	public long count() {
		return jewelPurchaseDetailsRepository.count();
	}
	
	
	
	public JewelPurchaseDetails saveAndFlush(JewelPurchaseDetails entity) {
		return jewelPurchaseDetailsRepository.saveAndFlush(entity);
	}
	
}
