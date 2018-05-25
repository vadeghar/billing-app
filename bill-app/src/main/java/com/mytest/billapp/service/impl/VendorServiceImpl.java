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
	
	public List<Vendor> save(List<Vendor> entities) {
		return vendorRepository.save(entities);
	}
	
	public void flush() {
		vendorRepository.flush();
	}
	
	public void deleteById(Long id) {
		vendorRepository.delete(id);
	}
	
	public void delete(Vendor entity) {
		vendorRepository.delete(entity);
	}
	
	public void deleteAll(List<Vendor> entities) {
		vendorRepository.delete(entities);
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
	public Vendor findById(Long id) {
		return vendorRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return vendorRepository.exists(id);
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

	@Override
	public List<Vendor> saveAll(List<Vendor> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
