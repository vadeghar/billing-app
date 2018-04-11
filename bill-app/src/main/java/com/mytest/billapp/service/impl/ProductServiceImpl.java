package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Product;
import com.mytest.billapp.repsitory.ProductRepository;
import com.mytest.billapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository; 
	
	public Product save(Product entity) {
		return productRepository.save(entity);
	}
	
	public List<Product> saveAll(List<Product> entities) {
		return productRepository.saveAll(entities);
	}
	
	public void flush() {
		productRepository.flush();
	}
	
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	
	public void delete(Product entity) {
		productRepository.delete(entity);
	}
	
	public void deleteAll(List<Product> entities) {
		productRepository.deleteAll(entities);
	}
	
	public void deleteInBatch(List<Product> entities) {
		productRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		productRepository.deleteAll();
	}
	
	public Product getOne(Long id) {
		return productRepository.getOne(id);
	}
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return productRepository.existsById(id);
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}
	
	public long count() {
		return productRepository.count();
	}
	
	
	
	public Product saveAndFlush(Product entity) {
		return productRepository.saveAndFlush(entity);
	}
	
}
