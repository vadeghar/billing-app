package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Stock;
import com.mytest.billapp.repsitory.StockRepository;
import com.mytest.billapp.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockRepository stockRepository; 
	
	public Stock save(Stock entity) {
		return stockRepository.save(entity);
	}
	
	public List<Stock> saveAll(List<Stock> entities) {
		return stockRepository.saveAll(entities);
	}
	
	public void flush() {
		stockRepository.flush();
	}
	
	public void deleteById(Long id) {
		stockRepository.deleteById(id);
	}
	
	public void delete(Stock entity) {
		stockRepository.delete(entity);
	}
	
	public void deleteAll(List<Stock> entities) {
		stockRepository.deleteAll(entities);
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
	public Optional<Stock> findById(Long id) {
		return stockRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return stockRepository.existsById(id);
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
	
}
