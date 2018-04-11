package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Purchase;

public interface PurchaseService {
	
	public Purchase save(Purchase entity);
	public List<Purchase> saveAll(List<Purchase> entities);
	public Purchase saveAndFlush(Purchase entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Purchase entity);
	public void deleteAll(List<Purchase> entities);
	public void deleteInBatch(List<Purchase> entities);
	public void deleteAll();
	public Purchase getOne(Long id);
	public Optional<Purchase> findById(Long id);
	public boolean existsById(Long id);
	public List<Purchase> findAll();
	public List<Purchase> findAll(Sort sort);
	public long count();

}
