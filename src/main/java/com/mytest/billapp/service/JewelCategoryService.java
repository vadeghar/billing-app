package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.JewelCategory;

public interface JewelCategoryService {
	
	public JewelCategory save(JewelCategory entity);
	public List<JewelCategory> saveAll(List<JewelCategory> entities);
	public JewelCategory saveAndFlush(JewelCategory entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(JewelCategory entity);
	public void deleteAll(List<JewelCategory> entities);
	public void deleteInBatch(List<JewelCategory> entities);
	public void deleteAll();
	public JewelCategory getOne(Long id);
	public JewelCategory findById(Long id);
	public boolean existsById(Long id);
	public List<JewelCategory> findAll();
	public List<JewelCategory> findAll(Sort sort);
	public long count();

}
