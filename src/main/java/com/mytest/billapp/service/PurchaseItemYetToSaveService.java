package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.PurchaseItemYetToSave;

public interface PurchaseItemYetToSaveService {
	
	public PurchaseItemYetToSave save(PurchaseItemYetToSave entity);
	public List<PurchaseItemYetToSave> saveAll(List<PurchaseItemYetToSave> entities);
	public PurchaseItemYetToSave saveAndFlush(PurchaseItemYetToSave entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(PurchaseItemYetToSave entity);
	public void deleteAll(List<PurchaseItemYetToSave> entities);
	public void deleteInBatch(List<PurchaseItemYetToSave> entities);
	public void deleteAll();
	public PurchaseItemYetToSave getOne(Long id);
	public Optional<PurchaseItemYetToSave> findById(Long id);
	public boolean existsById(Long id);
	public List<PurchaseItemYetToSave> findAll();
	public List<PurchaseItemYetToSave> findAll(Sort sort);
	public long count();

}
