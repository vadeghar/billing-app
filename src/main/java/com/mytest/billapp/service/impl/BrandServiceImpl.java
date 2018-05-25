package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Brand;
import com.mytest.billapp.repsitory.BrandRepository;
import com.mytest.billapp.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	BrandRepository brandRepository; 
	
	public Brand save(Brand entity) {
		return brandRepository.save(entity);
	}
	
	public List<Brand> saveAll(List<Brand> entities) {
		return brandRepository.save(entities);
	}
	
	public void flush() {
		brandRepository.flush();
	}
	
	public void deleteById(Long id) {
		brandRepository.delete(id);
	}
	
	public void delete(Brand entity) {
		brandRepository.delete(entity);
	}
	
	public void deleteAll(List<Brand> entities) {
		brandRepository.delete(entities);
	}
	
	public void deleteInBatch(List<Brand> entities) {
		brandRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		brandRepository.deleteAll();
	}
	
	public Brand getOne(Long id) {
		return brandRepository.getOne(id);
	}
	public Brand findById(Long id) {
		return brandRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return brandRepository.exists(id);
	}
	
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}
	
	public List<Brand> findAll(Sort sort) {
		return brandRepository.findAll(sort);
	}
	
	public long count() {
		return brandRepository.count();
	}
	
	
	
	public Brand saveAndFlush(Brand entity) {
		return brandRepository.saveAndFlush(entity);
	}
	
}
