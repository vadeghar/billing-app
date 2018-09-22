package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.JewelPurchaseDetails;

public interface JewelPurchaseDetailsService {
	
	public JewelPurchaseDetails save(JewelPurchaseDetails entity);
	public List<JewelPurchaseDetails> saveAll(List<JewelPurchaseDetails> entities);
	public JewelPurchaseDetails saveAndFlush(JewelPurchaseDetails entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(JewelPurchaseDetails entity);
	public void deleteAll(List<JewelPurchaseDetails> entities);
	public void deleteInBatch(List<JewelPurchaseDetails> entities);
	public void deleteAll();
	public JewelPurchaseDetails getOne(Long id);
	public JewelPurchaseDetails findById(Long id);
	public boolean existsById(Long id);
	public List<JewelPurchaseDetails> findAll();
	public List<JewelPurchaseDetails> findAll(Sort sort);
	public long count();

}
