package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Stock;

public interface StockService {
	
	public Stock save(Stock entity);
	public List<Stock> saveAll(List<Stock> entities);
	public Stock saveAndFlush(Stock entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Stock entity);
	public void deleteAll(List<Stock> entities);
	public void deleteInBatch(List<Stock> entities);
	public void deleteAll();
	public Stock getOne(Long id);
	public Optional<Stock> findById(Long id);
	public boolean existsById(Long id);
	public List<Stock> findAll();
	public List<Stock> findAll(Sort sort);
	public long count();

}
