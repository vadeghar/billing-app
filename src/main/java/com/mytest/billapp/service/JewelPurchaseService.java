package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.JewelPurchase;

public interface JewelPurchaseService {
	
	public JewelPurchase save(JewelPurchase entity);
	public List<JewelPurchase> saveAll(List<JewelPurchase> entities);
	public JewelPurchase saveAndFlush(JewelPurchase entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(JewelPurchase entity);
	public void deleteAll(List<JewelPurchase> entities);
	public void deleteInBatch(List<JewelPurchase> entities);
	public void deleteAll();
	public JewelPurchase getOne(Long id);
	public JewelPurchase findById(Long id);
	public boolean existsById(Long id);
	public List<JewelPurchase> findAll();
	public List<JewelPurchase> findAll(Sort sort);
	public long count();

}
