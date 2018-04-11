package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Brand;

public interface BrandService {
	
	public Brand save(Brand entity);
	public List<Brand> saveAll(List<Brand> entities);
	public Brand saveAndFlush(Brand entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Brand entity);
	public void deleteAll(List<Brand> entities);
	public void deleteInBatch(List<Brand> entities);
	public void deleteAll();
	public Brand getOne(Long id);
	public Optional<Brand> findById(Long id);
	public boolean existsById(Long id);
	public List<Brand> findAll();
	public List<Brand> findAll(Sort sort);
	public long count();

}
