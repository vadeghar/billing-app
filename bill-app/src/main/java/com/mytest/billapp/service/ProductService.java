package com.mytest.billapp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Product;

public interface ProductService {
	
	public Product save(Product entity);
	public List<Product> saveAll(List<Product> entities);
	public Product saveAndFlush(Product entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Product entity);
	public void deleteAll(List<Product> entities);
	public void deleteInBatch(List<Product> entities);
	public void deleteAll();
	public Product getOne(Long id);
	public Product findById(Long id);
	public boolean existsById(Long id);
	public List<Product> findAll();
	public List<Product> findAll(Sort sort);
	public long count();
	public Map<Integer, String> getProductSizeList(Long productId);
	public List<Product> getProductList();
	public Map<Integer, String> getProductTypes();

}
