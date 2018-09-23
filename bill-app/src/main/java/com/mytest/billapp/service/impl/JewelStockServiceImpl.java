package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.JewelStock;
import com.mytest.billapp.repsitory.JewelStockRepository;
import com.mytest.billapp.service.JewelStockService;

@Service
public class JewelStockServiceImpl implements JewelStockService {
	
	@Autowired
	JewelStockRepository jewelStockRepository; 
	
	public JewelStock save(JewelStock entity) {
		return jewelStockRepository.save(entity);
	}
	
	public List<JewelStock> saveAll(List<JewelStock> entities) {
		return jewelStockRepository.save(entities);
	}
	
	public void flush() {
		jewelStockRepository.flush();
	}
	
	public void deleteById(Long id) {
		jewelStockRepository.delete(id);
	}
	
	public void delete(JewelStock entity) {
		jewelStockRepository.delete(entity);
	}
	
	public void deleteAll(List<JewelStock> entities) {
		jewelStockRepository.delete(entities);
	}
	
	public void deleteInBatch(List<JewelStock> entities) {
		jewelStockRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		jewelStockRepository.deleteAll();
	}
	
	public JewelStock getOne(Long id) {
		return jewelStockRepository.getOne(id);
	}
	public JewelStock findById(Long id) {
		return jewelStockRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return jewelStockRepository.exists(id);
	}
	
	public List<JewelStock> findAll() {
		return jewelStockRepository.findAll();
	}
	
	public List<JewelStock> findAll(Sort sort) {
		return jewelStockRepository.findAll(sort);
	}
	
	public long count() {
		return jewelStockRepository.count();
	}
	
	
	
	public JewelStock saveAndFlush(JewelStock entity) {
		return jewelStockRepository.saveAndFlush(entity);
	}
	public JewelStock findByItemCode(String itemCode) {
		return jewelStockRepository.findByItemCode(itemCode);
	}
}
