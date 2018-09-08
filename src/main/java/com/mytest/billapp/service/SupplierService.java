package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.mytest.billapp.model.Supplier;

public interface SupplierService {
	@PreAuthorize("hasRole('ADMIN')")
	public Supplier save(Supplier entity);
	
	public List<Supplier> saveAll(List<Supplier> entities);
	public Supplier saveAndFlush(Supplier entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Supplier entity);
	public void deleteAll(List<Supplier> entities);
	public void deleteInBatch(List<Supplier> entities);
	public void deleteAll();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Supplier getOne(Long id);
	
	public Supplier findById(Long id);
	public boolean existsById(Long id);
	public List<Supplier> findAll();
	public List<Supplier> findAll(Sort sort);
	public long count();

}
