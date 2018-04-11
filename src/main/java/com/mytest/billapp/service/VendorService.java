package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Vendor;

public interface VendorService {
	
	public Vendor save(Vendor entity);
	public List<Vendor> saveAll(List<Vendor> entities);
	public Vendor saveAndFlush(Vendor entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Vendor entity);
	public void deleteAll(List<Vendor> entities);
	public void deleteInBatch(List<Vendor> entities);
	public void deleteAll();
	public Vendor getOne(Long id);
	public Optional<Vendor> findById(Long id);
	public boolean existsById(Long id);
	public List<Vendor> findAll();
	public List<Vendor> findAll(Sort sort);
	public long count();

}
