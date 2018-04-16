package com.mytest.billapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.repsitory.PurchaseRepository;
import com.mytest.billapp.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
	
	@Autowired
	PurchaseRepository purchaseRepository; 
	
	public Purchase save(Purchase entity) {
		return purchaseRepository.save(entity);
	}
	
	public List<Purchase> saveAll(List<Purchase> entities) {
		return purchaseRepository.saveAll(entities);
	}
	
	public void flush() {
		purchaseRepository.flush();
	}
	
	public void deleteById(Long id) {
		purchaseRepository.deleteById(id);
	}
	
	public void delete(Purchase entity) {
		purchaseRepository.delete(entity);
	}
	
	public void deleteAll(List<Purchase> entities) {
		purchaseRepository.deleteAll(entities);
	}
	
	public void deleteInBatch(List<Purchase> entities) {
		purchaseRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		purchaseRepository.deleteAll();
	}
	
	public PurchaseDTO getOne(Long id) {
		return null; // purchaseRepository.getOne(id);
	}
	public PurchaseDTO findById(Long id) {
		if(id == null || id == 0) return new PurchaseDTO();
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		Optional<Purchase> purchase = purchaseRepository.findById(id);
		Purchase p = purchase.get();
		if(purchase != null && p != null) {
			purchaseDTO = getDTOFromModel(p);
		}
		
		
		return purchaseDTO;
	}
	
	public boolean existsById(Long id) {
		return purchaseRepository.existsById(id);
	}
	
	public List<PurchaseDTO> findAll() {
		List<Purchase> purchaseList = purchaseRepository.findAll();
		List<PurchaseDTO> dtoList = new ArrayList<PurchaseDTO>();
		if(!CollectionUtils.isEmpty(purchaseList)) {
			purchaseList.stream().forEach(p ->  dtoList.add(getDTOFromModel(p)));
		}
		return dtoList;
	}
	
	public List<Purchase> findAll(Sort sort) {
		return purchaseRepository.findAll(sort);
	}
	
	public long count() {
		return purchaseRepository.count();
	}
	
	
	
	public Purchase saveAndFlush(Purchase entity) {
		return purchaseRepository.saveAndFlush(entity);
	}
	
	private PurchaseDTO getDTOFromModel(Purchase p) {
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		if(p != null) {
			purchaseDTO.setId(p.getId());
			purchaseDTO.setBillDate(sdf.format(p.getBillDate()));
			purchaseDTO.setBillNo(p.getBillNo());
			purchaseDTO.setBillTotal(p.getBillTotal());
			purchaseDTO.setDiscount(p.getDiscount());
			purchaseDTO.setDiscountType(p.getDiscountType());
			purchaseDTO.setEntryDate(sdf.format(p.getEntryDate()));
			purchaseDTO.setNetTotal(p.getNetTotal());
			purchaseDTO.setVendorId(p.getVendor().getId());
			
			if(p.getPurchaseItemSet().size() > 0) {
				p.getPurchaseItemSet().stream().forEach(pi -> {
					PurchaseItemDTO dto = new PurchaseItemDTO();
					dto.setId(pi.getId());
					dto.setItemCode(pi.getItemCode());
					dto.setMargin(pi.getMargin());
					dto.setPricePerUnit(pi.getPricePerPc());
					dto.setProductId(pi.getProduct().getId());
					dto.setQuantity(pi.getQuantity());
					Double salePrice = 0.0;
					if(pi.getMarginType().equals("%")){
						salePrice = dto.getPricePerUnit() * dto.getMargin() / 100;
					}else {
						salePrice = dto.getPricePerUnit() + dto.getMargin();
					}
					dto.setSalePrice(salePrice);
					dto.setSize(pi.getSize());
					dto.setSrNo(pi.getSrNo());
					dto.setTotal(pi.getTotalPrice());
					purchaseDTO.getPurchaseItems().add(dto);
				});
			}
			
		}
		return purchaseDTO;
	}
	
}
