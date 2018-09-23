package com.mytest.billapp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.JewelCategory;
import com.mytest.billapp.model.JewelPurchase;
import com.mytest.billapp.model.JewelPurchaseDetails;
import com.mytest.billapp.model.JewelStock;
import com.mytest.billapp.repsitory.JewelPurchaseDetailsRepository;
import com.mytest.billapp.repsitory.JewelPurchaseRepository;
import com.mytest.billapp.service.JewelCategoryService;
import com.mytest.billapp.service.JewelPurchaseService;
import com.mytest.billapp.service.JewelStockService;
import com.mytest.billapp.utils.AppUtils;

@Service
public class JewelPurchaseServiceImpl implements JewelPurchaseService {
	
	@Autowired
	JewelPurchaseRepository jewelPurchaseRepository; 
	
	@Autowired
	JewelPurchaseDetailsRepository jewelPurchaseDetailsRepository;
	
	@Autowired
	JewelCategoryService jewelCategoryService;
	
	@Autowired
	JewelStockService jewelStockService;
	
	public JewelPurchase save(JewelPurchase jewelPurchase) {
		
		JewelPurchase dbJewelPurchase =  new JewelPurchase();
		List<JewelPurchaseDetails> dbJewelPurchaseDetailsList = new ArrayList<>();
		if(jewelPurchase != null && AppUtils.isValidNonZeroLong(jewelPurchase.getId())) 
			dbJewelPurchase = getOne(jewelPurchase.getId());
		BigDecimal totalAmount = new BigDecimal(0.0);
		dbJewelPurchase.setPurchaseDate(jewelPurchase.getPurchaseDate());
		dbJewelPurchase.setSupplierId(jewelPurchase.getSupplierId());
		dbJewelPurchase.setTotalAmount(totalAmount);
		if(CollectionUtils.isNotEmpty(jewelPurchase.getJewelPurchaseDetails())) {
			for(JewelPurchaseDetails jewelPurchaseDetails : jewelPurchase.getJewelPurchaseDetails() ) {
				JewelPurchaseDetails dbJewelPurchaseDetails = new JewelPurchaseDetails();
				if(AppUtils.isValidNonZeroLong(jewelPurchaseDetails.getId())) 
					dbJewelPurchaseDetails = jewelPurchaseDetailsRepository.findOne(jewelPurchaseDetails.getId());
				JewelCategory jewelCategory = jewelCategoryService.getOne(jewelPurchaseDetails.getCategoryId());
				dbJewelPurchaseDetails.setCategoryId(jewelPurchaseDetails.getCategoryId());
				dbJewelPurchaseDetails.setTotalWieght(jewelPurchaseDetails.getTotalWieght());
				dbJewelPurchaseDetails.setQuantity(jewelPurchaseDetails.getQuantity());
				BigDecimal avgWeight = new BigDecimal(jewelPurchaseDetails.getTotalWieght().floatValue() / jewelPurchaseDetails.getQuantity());
				dbJewelPurchaseDetails.setAvgWieght(AppUtils.roundUp(avgWeight));
				dbJewelPurchaseDetails.setQuality(jewelPurchaseDetails.getQuality());
				dbJewelPurchaseDetails.setJewelPurchase(dbJewelPurchase);
				dbJewelPurchaseDetails.setRateCutDate(jewelPurchaseDetails.getRateCutDate());
				dbJewelPurchaseDetails.setRateCutAt(jewelPurchaseDetails.getRateCutAt());
				dbJewelPurchaseDetails.setMakingChargePerPc(jewelPurchaseDetails.getMakingChargePerPc());
				dbJewelPurchaseDetails.setTaxRate(jewelPurchaseDetails.getTaxRate());
				dbJewelPurchaseDetails.setWastagePerPc(jewelPurchaseDetails.getWastagePerPc());
				dbJewelPurchaseDetails.setPurchaseTotal(getPurchaseTotal(dbJewelPurchaseDetails));
				dbJewelPurchaseDetails.setGeneratedItemCode("SJ-"+dbJewelPurchase.getSupplierId()+jewelCategory.getName().toUpperCase());
				dbJewelPurchaseDetailsList.add(dbJewelPurchaseDetails);
			}
		}
		dbJewelPurchase.setJewelPurchaseDetails(dbJewelPurchaseDetailsList);
		dbJewelPurchase.setTotalAmount(getAllPurchaseDetalsTotal(dbJewelPurchaseDetailsList));
		JewelPurchase savedJewelPurchase = jewelPurchaseRepository.save(dbJewelPurchase);
		updateJewelStock(savedJewelPurchase);
		return savedJewelPurchase;
	}
	
	private void updateJewelStock(JewelPurchase savedJewelPurchase) {
		for(JewelPurchaseDetails jewelPurchaseDetails : savedJewelPurchase.getJewelPurchaseDetails()) {
			JewelStock jewelStock = null;
			if(!StringUtils.isEmpty(jewelPurchaseDetails.getGeneratedItemCode())) {
				jewelStock = jewelStockService.findByItemCode(jewelPurchaseDetails.getGeneratedItemCode());
			}
			if(jewelStock == null)
				jewelStock = new JewelStock();
			jewelStock.setItemCode(jewelPurchaseDetails.getGeneratedItemCode());
			jewelStock.setCategoryId(jewelPurchaseDetails.getCategoryId());
			Integer quantity = jewelStock.getQuantity() + jewelPurchaseDetails.getQuantity();
			BigDecimal totalWeight = jewelStock.getTotalWeight().add(jewelPurchaseDetails.getTotalWieght());
			jewelStock.setQuantity(quantity);
			jewelStock.setTotalWeight(totalWeight);
			jewelStockService.save(jewelStock);
		}
	}

	private BigDecimal getAllPurchaseDetalsTotal(List<JewelPurchaseDetails> dbJewelPurchaseDetailsList) {
		BigDecimal purchaseTotal = BigDecimal.ZERO;
		for(JewelPurchaseDetails detail: dbJewelPurchaseDetailsList) {
			purchaseTotal = purchaseTotal.add(detail.getPurchaseTotal());
		}
		return AppUtils.roundUp(purchaseTotal);
	}

	private BigDecimal getPurchaseTotal(JewelPurchaseDetails dbJewelPurchaseDetails) {
		BigDecimal purchaseTotal = BigDecimal.ZERO;
		if(AppUtils.isValidNonZeroNumber(dbJewelPurchaseDetails.getRateCutAt())) {
			if(AppUtils.isValidNonZeroNumber(dbJewelPurchaseDetails.getTotalWieght()) && AppUtils.isValidNonZeroNumber(dbJewelPurchaseDetails.getQuality())) {
				BigDecimal netWeight = dbJewelPurchaseDetails.getTotalWieght().multiply(new BigDecimal(dbJewelPurchaseDetails.getQuality())).divide(new BigDecimal(100));
				System.out.println("Net weight: "+netWeight.doubleValue());
				purchaseTotal = netWeight.multiply(dbJewelPurchaseDetails.getRateCutAt());
				System.out.println("Purchase Total: "+purchaseTotal.doubleValue());
				if(AppUtils.isValidNonZeroNumber(dbJewelPurchaseDetails.getMakingChargePerPc())) {
					BigDecimal mc = new BigDecimal(dbJewelPurchaseDetails.getQuantity()).multiply(dbJewelPurchaseDetails.getMakingChargePerPc());
					System.out.println("Making Charge Total: "+mc.doubleValue());
					purchaseTotal = purchaseTotal.add(mc);
					System.out.println("Purchase Total: "+purchaseTotal.doubleValue());
				}
				if(AppUtils.isValidNonZeroNumber(dbJewelPurchaseDetails.getWastagePerPc())) {
					BigDecimal wastageTotal = new BigDecimal(dbJewelPurchaseDetails.getQuantity()).multiply(dbJewelPurchaseDetails.getWastagePerPc());
					System.out.println("Wastage Total: "+wastageTotal.doubleValue());
					purchaseTotal = purchaseTotal.add(wastageTotal);
					System.out.println("Purchase Total: "+purchaseTotal.doubleValue());
				}
				if(AppUtils.isValidNonZeroNumber(dbJewelPurchaseDetails.getTaxRate())) {
					BigDecimal taxToal = purchaseTotal.multiply(dbJewelPurchaseDetails.getTaxRate()).divide(new BigDecimal(100));
					System.out.println("Tax Total: "+taxToal.doubleValue());
					purchaseTotal = purchaseTotal.add(taxToal);
					System.out.println("Purchase Total: "+purchaseTotal.doubleValue());
				}
			}
		}
		return AppUtils.roundUp(purchaseTotal);
	}

	public List<JewelPurchase> saveAll(List<JewelPurchase> entities) {
		return jewelPurchaseRepository.save(entities);
	}
	
	public void flush() {
		jewelPurchaseRepository.flush();
	}
	
	public void deleteById(Long id) {
		jewelPurchaseRepository.delete(id);
	}
	
	public void delete(JewelPurchase entity) {
		jewelPurchaseRepository.delete(entity);
	}
	
	public void deleteAll(List<JewelPurchase> entities) {
		jewelPurchaseRepository.delete(entities);
	}
	
	public void deleteInBatch(List<JewelPurchase> entities) {
		jewelPurchaseRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		jewelPurchaseRepository.deleteAll();
	}
	
	public JewelPurchase getOne(Long id) {
		return jewelPurchaseRepository.getOne(id);
	}
	public JewelPurchase findById(Long id) {
		return jewelPurchaseRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return jewelPurchaseRepository.exists(id);
	}
	
	public List<JewelPurchase> findAll() {
		return jewelPurchaseRepository.findAll();
	}
	
	public List<JewelPurchase> findAll(Sort sort) {
		return jewelPurchaseRepository.findAll(sort);
	}
	
	public long count() {
		return jewelPurchaseRepository.count();
	}
	
	
	
	public JewelPurchase saveAndFlush(JewelPurchase entity) {
		return jewelPurchaseRepository.saveAndFlush(entity);
	}
	
}
