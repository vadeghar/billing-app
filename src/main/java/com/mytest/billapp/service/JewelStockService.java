package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.JewelStock;

public interface JewelStockService {
	
	public JewelStock save(JewelStock entity);
	public List<JewelStock> saveAll(List<JewelStock> entities);
	public JewelStock saveAndFlush(JewelStock entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(JewelStock entity);
	public void deleteAll(List<JewelStock> entities);
	public void deleteInBatch(List<JewelStock> entities);
	public void deleteAll();
	public JewelStock getOne(Long id);
	public JewelStock findById(Long id);
	public boolean existsById(Long id);
	public List<JewelStock> findAll();
	public List<JewelStock> findAll(Sort sort);
	public long count();
	public JewelStock findByItemCode(String itemCode);

}
