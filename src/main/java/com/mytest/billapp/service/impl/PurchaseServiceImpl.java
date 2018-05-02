package com.mytest.billapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;

import com.mysql.jdbc.StringUtils;
import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.model.PurchaseItem;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.PurchaseRepository;
import com.mytest.billapp.repsitory.VendorRepository;
import com.mytest.billapp.service.PurchaseService;
import com.mytest.billapp.utils.ProductSizeEnum;
import com.mytest.billapp.utils.ProductTypeEnum;
import com.mytest.billapp.utils.Utils;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	PurchaseRepository purchaseRepository; 
	
	@Autowired
	VendorRepository vendorRepository;
	
	public PurchaseDTO save(PurchaseDTO entity) {
		try {
			Set<PurchaseItem> purchaseItemSet = new HashSet<PurchaseItem>();
			//PurchaseItemDTO purchaseItemDTO = entity.getPurchaseItemDTO();
			PurchaseItem purchaseItem = new PurchaseItem();
			Purchase purchase = null;
			if(entity.getId() != null && entity.getId().longValue() > 0) {
				purchase = purchaseRepository.findById(entity.getId()).get();
			}else {
				purchase = new Purchase();
			}
			if(!CollectionUtils.isEmpty(entity.getPurchaseItems())) {
				for(PurchaseItemDTO itemDTO : entity.getPurchaseItems()) {
					purchaseItem.setId(itemDTO.getId());
					purchaseItem.setItemCode(itemDTO.getItemCode());
					purchaseItem.setMargin(itemDTO.getMargin());
					purchaseItem.setMarginType(itemDTO.getMarginType());
					//purchaseItem.setModel(itemDTO.getModel());
					purchaseItem.setPricePerPc(itemDTO.getPricePerUnit());
					purchaseItem.setProductTypeText(itemDTO.getProductId().toString());
					purchaseItem.setQuantity(itemDTO.getQuantity());
					purchaseItem.setSize(itemDTO.getSize());
					purchaseItem.setSrNo(itemDTO.getSrNo());
					purchaseItem.setTotalPrice(itemDTO.getTotal());
					purchase.addPurhcaseItem(purchaseItem);
					//purchaseItemSet.add(purchaseItem);
				}
			}
			purchase.setBillDate(entity.getBillDate() != null ? sdf.parse(entity.getBillDate()) : null);
			purchase.setBillNo(entity.getBillNo());
			purchase.setDiscountType(entity.getDiscountType());
			purchase.setVendor(vendorRepository.getOne(entity.getVendorId()));
			//purchase.setPurchaseItemSet(purchaseItemSet);
			setTotalAdnDiscount(purchase, entity);
			
			Purchase p =purchaseRepository.save(purchase);
			
			return findById(p.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return purchaseRepository.save(entity);
		
		return null;
	}
	
	private void setTotalAdnDiscount(Purchase purchase, PurchaseDTO entity) {
		Set<PurchaseItem> set = purchase.getPurchaseItemSet();
		Double totalOfAllItems = new Double(0);
		Double discount = new Double(0);
		if(set != null && set.size() > 0){
			for(PurchaseItem purchaseItem : set) {
				totalOfAllItems = totalOfAllItems + purchaseItem.getTotalPrice();
			}
			if(entity.getDiscountType().equals("%"))
				discount = totalOfAllItems * (entity.getDiscount() / 100);
			else
				discount = totalOfAllItems - entity.getDiscount();
		}
		
		
		purchase.setBillTotal(totalOfAllItems);
		purchase.setDiscount(discount);
		purchase.setNetTotal(totalOfAllItems - discount);
		
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
			purchaseDTO.setBillTotal(Utils.formatDecimals(p.getBillTotal()));
			purchaseDTO.setDiscount(Utils.formatDecimals(p.getDiscount()));
			purchaseDTO.setDiscountType(p.getDiscountType());
			purchaseDTO.setEntryDate(sdf.format(p.getEntryDate()));
			purchaseDTO.setNetTotal(Utils.formatDecimals(p.getNetTotal()));
			Vendor v = p.getVendor();
			if(v != null)
			{
				purchaseDTO.setVendorCity(v.getCity());
				purchaseDTO.setVendorGst(v.getGstNo());
				purchaseDTO.setVendorMobile(v.getMobile());
				purchaseDTO.setVendorName(v.getName());
			}
			purchaseDTO.setVendorId(p.getVendor() != null ? p.getVendor().getId() : 0);
			
			if(p.getPurchaseItemSet().size() > 0) {
				p.getPurchaseItemSet().stream().forEach(pi -> {
					PurchaseItemDTO dto = new PurchaseItemDTO();
					dto.setId(pi.getId());
					dto.setItemCode(pi.getItemCode());
					dto.setMargin(pi.getMargin());
					dto.setPricePerUnit(pi.getPricePerPc());
					if(pi.getProductTypeText() != null) {
						dto.setProductId(ProductTypeEnum.getById(Long.parseLong(pi.getProductTypeText())).id);
						dto.setProductType(ProductTypeEnum.getById(dto.getProductId().longValue()).code);
					}
					dto.setQuantity(pi.getQuantity());
					Double salePrice = 0.0;
					if(pi.getMarginType() != null && pi.getMarginType().equals("%")){
						salePrice = dto.getPricePerUnit() * dto.getMargin() / 100;
					}else {
						salePrice = dto.getPricePerUnit() + dto.getMargin();
					}
					if(!StringUtils.isNullOrEmpty(pi.getSize())){
						dto.setSize(pi.getSize());
						dto.setSizeName(ProductSizeEnum.getById(Long.parseLong(pi.getSize())).getSize());
					}
					dto.setSalePrice(salePrice);
					
					dto.setSrNo(pi.getSrNo());
					dto.setTotal(pi.getTotalPrice());
					purchaseDTO.getPurchaseItems().add(dto);
				});
			}
			
		}
		return purchaseDTO;
	}
	
}
