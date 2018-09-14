package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.JewelCategory;
import com.mytest.billapp.repsitory.JewelCategoryRepository;
import com.mytest.billapp.service.JewelCategoryService;

@Service
public class JewelCategoryServiceImpl implements JewelCategoryService {
	
	@Autowired
	JewelCategoryRepository jewelCategoryRepository; 
	
	public JewelCategory save(JewelCategory entity) {
		return jewelCategoryRepository.save(entity);
	}
	
	public List<JewelCategory> saveAll(List<JewelCategory> entities) {
		return jewelCategoryRepository.save(entities);
	}
	
	public void flush() {
		jewelCategoryRepository.flush();
	}
	
	public void deleteById(Long id) {
		jewelCategoryRepository.delete(id);
	}
	
	public void delete(JewelCategory entity) {
		jewelCategoryRepository.delete(entity);
	}
	
	public void deleteAll(List<JewelCategory> entities) {
		jewelCategoryRepository.delete(entities);
	}
	
	public void deleteInBatch(List<JewelCategory> entities) {
		jewelCategoryRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		jewelCategoryRepository.deleteAll();
	}
	
	public JewelCategory getOne(Long id) {
		return jewelCategoryRepository.getOne(id);
	}
	public JewelCategory findById(Long id) {
		return jewelCategoryRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return jewelCategoryRepository.exists(id);
	}
	
	public List<JewelCategory> findAll() {
		return jewelCategoryRepository.findAll();
	}
	
	public List<JewelCategory> findAll(Sort sort) {
		return jewelCategoryRepository.findAll(sort);
	}
	
	public long count() {
		return jewelCategoryRepository.count();
	}
	
	
	
	public JewelCategory saveAndFlush(JewelCategory entity) {
		return jewelCategoryRepository.saveAndFlush(entity);
	}
	
}
