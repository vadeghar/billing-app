package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.ProductItems;

public interface ProductItemsService {
	
	public ProductItems save(ProductItems entity);
	public List<ProductItems> saveAll(List<ProductItems> entities);
	public ProductItems saveAndFlush(ProductItems entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(ProductItems entity);
	public void deleteAll(List<ProductItems> entities);
	public void deleteInBatch(List<ProductItems> entities);
	public void deleteAll();
	public ProductItems getOne(Long id);
	public ProductItems findById(Long id);
	public boolean existsById(Long id);
	public List<ProductItems> findAll();
	public List<ProductItems> findAll(Sort sort);
	public long count();
	public List<ProductItems> findAllByProductId(Long productId);
}
