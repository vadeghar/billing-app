package com.mytest.billapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public List<Product> getProductList() {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(new Long(1), "Jeans", new Long(0)));
		productList.add(new Product(new Long(2), "T-Shirts", new Long(0)));
		productList.add(new Product(new Long(3), "Shirts", new Long(0)));
		productList.add(new Product(new Long(4), "Other", new Long(0)));
		return productList;
	}
	
	
	public List<Product> getProductSizeList(Long productId) {
		return productSizeList.stream().filter( p -> p.getBrandId().longValue() == productId.longValue()).collect(Collectors.toList());
	}
	
	private static List<Product> productSizeList = new ArrayList<>();
	static {
		
		productSizeList.add(new Product(new Long(1), "28-30-32", new Long(1)));
		productSizeList.add(new Product(new Long(2), "34-36", new Long(1)));
		productSizeList.add(new Product(new Long(3), "38", new Long(1)));
		
		
		productSizeList.add(new Product(new Long(4), "S", new Long(2)));
		productSizeList.add(new Product(new Long(5), "M", new Long(2)));
		productSizeList.add(new Product(new Long(6), "L", new Long(2)));
		
		productSizeList.add(new Product(new Long(4), "S", new Long(3)));
		productSizeList.add(new Product(new Long(5), "M", new Long(3)));
		productSizeList.add(new Product(new Long(6), "L", new Long(3)));
		
	}
	
}
