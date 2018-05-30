package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.ProductItems;
import com.mytest.billapp.repsitory.ProductItemsRepository;
import com.mytest.billapp.service.ProductItemsService;

@Service
public class ProductItemsServiceImpl implements ProductItemsService {
	
	@Autowired
	ProductItemsRepository productItemsRepository; 
	
	public ProductItems save(ProductItems entity) {
		return productItemsRepository.save(entity);
	}
	
	public List<ProductItems> save(List<ProductItems> entities) {
		return productItemsRepository.save(entities);
	}
	
	public void flush() {
		productItemsRepository.flush();
	}
	
	public void deleteById(Long id) {
		productItemsRepository.delete(id);
	}
	
	public void delete(ProductItems entity) {
		productItemsRepository.delete(entity);
	}
	
	public void deleteAll(List<ProductItems> entities) {
		productItemsRepository.delete(entities);
	}
	
	public void deleteInBatch(List<ProductItems> entities) {
		productItemsRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		productItemsRepository.deleteAll();
	}
	
	public ProductItems getOne(Long id) {
		return productItemsRepository.getOne(id);
	}
	public ProductItems findById(Long id) {
		return productItemsRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return productItemsRepository.exists(id);
	}
	
	public List<ProductItems> findAll() {
		return productItemsRepository.findAll();
	}
	
	public List<ProductItems> findAll(Sort sort) {
		return productItemsRepository.findAll(sort);
	}
	
	public long count() {
		return productItemsRepository.count();
	}
	
	
	
	public ProductItems saveAndFlush(ProductItems entity) {
		return productItemsRepository.saveAndFlush(entity);
	}
	
	
	@Override
	public List<ProductItems> saveAll(List<ProductItems> entities) {
		return productItemsRepository.save(entities);
	}

	@Override
	public List<ProductItems> findAllByProductId(Long productId) {
		return productItemsRepository.findByProductId(productId);
	}
	
}
