package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.VendorRepository;
import com.mytest.billapp.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorRepository vendorRepository; 
	
	public Vendor save(Vendor entity) {
		return vendorRepository.save(entity);
	}
	
	public List<Vendor> saveAll(List<Vendor> entities) {
		return vendorRepository.saveAll(entities);
	}
	
	public void flush() {
		vendorRepository.flush();
	}
	
	public void deleteById(Long id) {
		vendorRepository.deleteById(id);
	}
	
	public void delete(Vendor entity) {
		vendorRepository.delete(entity);
	}
	
	public void deleteAll(List<Vendor> entities) {
		vendorRepository.deleteAll(entities);
	}
	
	public void deleteInBatch(List<Vendor> entities) {
		vendorRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		vendorRepository.deleteAll();
	}
	
	public Vendor getOne(Long id) {
		return vendorRepository.getOne(id);
	}
	public Optional<Vendor> findById(Long id) {
		return vendorRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return vendorRepository.existsById(id);
	}
	
	public List<Vendor> findAll() {
		return vendorRepository.findAll();
	}
	
	public List<Vendor> findAll(Sort sort) {
		return vendorRepository.findAll(sort);
	}
	
	public long count() {
		return vendorRepository.count();
	}
	
	
	
	public Vendor saveAndFlush(Vendor entity) {
		return vendorRepository.saveAndFlush(entity);
	}
	
}
