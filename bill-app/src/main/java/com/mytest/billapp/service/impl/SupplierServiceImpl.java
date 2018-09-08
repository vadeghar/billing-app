package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Supplier;
import com.mytest.billapp.repsitory.SupplierRepository;
import com.mytest.billapp.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	SupplierRepository supplierRepository; 
	
	public Supplier save(Supplier entity) {
		return supplierRepository.save(entity);
	}
	
	public List<Supplier> save(List<Supplier> entities) {
		return supplierRepository.save(entities);
	}
	
	public void flush() {
		supplierRepository.flush();
	}
	
	public void deleteById(Long id) {
		supplierRepository.delete(id);
	}
	
	public void delete(Supplier entity) {
		supplierRepository.delete(entity);
	}
	
	public void deleteAll(List<Supplier> entities) {
		supplierRepository.delete(entities);
	}
	
	public void deleteInBatch(List<Supplier> entities) {
		supplierRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		supplierRepository.deleteAll();
	}
	
	public Supplier getOne(Long id) {
		return supplierRepository.getOne(id);
	}
	public Supplier findById(Long id) {
		return supplierRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return supplierRepository.exists(id);
	}
	
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}
	
	public List<Supplier> findAll(Sort sort) {
		return supplierRepository.findAll(sort);
	}
	
	public long count() {
		return supplierRepository.count();
	}
	
	
	
	public Supplier saveAndFlush(Supplier entity) {
		return supplierRepository.saveAndFlush(entity);
	}

	@Override
	public List<Supplier> saveAll(List<Supplier> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
