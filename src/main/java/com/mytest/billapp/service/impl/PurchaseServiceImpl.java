package com.mytest.billapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.model.PurchaseItem;
import com.mytest.billapp.model.Stock;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.PurchaseItemRepository;
import com.mytest.billapp.repsitory.PurchaseRepository;
import com.mytest.billapp.repsitory.VendorRepository;
import com.mytest.billapp.service.PurchaseService;
import com.mytest.billapp.service.StockService;
import com.mytest.billapp.utils.ProductSizeEnum;
import com.mytest.billapp.utils.ProductTypeEnum;
import com.mytest.billapp.utils.Utils;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	PurchaseRepository purchaseRepository; 
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository; 
	
	@Autowired
	VendorRepository vendorRepository;
	
	public PurchaseDTO save(PurchaseDTO entity) {
		try {
			Purchase purchase = null;
			if(entity.getId() != null && entity.getId().longValue() > 0) {
				purchase = purchaseRepository.findOne(entity.getId());
			}else {
				purchase = new Purchase();
			}
			purchase.setBillDate(entity.getBillDate() != null ? sdf.parse(entity.getBillDate()) : null);
			purchase.setBillNo(entity.getBillNo());
			purchase.setDiscountType(entity.getDiscountType());
			purchase.setVendor(vendorRepository.getOne(entity.getVendorId()));
			//purchase.setPurchaseItemSet(purchaseItemSet);
			
			
			Purchase p = purchaseRepository.save(purchase);
			if(!CollectionUtils.isEmpty(entity.getPurchaseItems())) {
				saveAllPurchaseItem(entity.getPurchaseItems(), p.getId());
			}
			setTotalAdnDiscount(p, entity);
			purchaseRepository.save(purchase);
			PurchaseDTO dbPurchaseDTO = findById(p.getId());
			return dbPurchaseDTO;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return purchaseRepository.save(entity);
		
		return null;
	}
	
	private void saveAllPurchaseItem(List<PurchaseItemDTO> purchaseItems, Long purchaseId) {
		List<PurchaseItem> purchaseItemList = new ArrayList<PurchaseItem>();
		purchaseItems.stream().forEach(purchaseItemDTO -> {
			PurchaseItem purchaseItem = null;
			if(purchaseItemDTO.getId() == null || purchaseItemDTO.getId().longValue() == 0) 
				purchaseItem = new PurchaseItem();
			else 
				purchaseItem = purchaseItemRepository.getOne(purchaseItemDTO.getId());
			
			purchaseItem.setItemCode(purchaseItemDTO.getItemCode());
			purchaseItem.setMargin(purchaseItemDTO.getMargin());
			purchaseItem.setMarginType(purchaseItemDTO.getMarginType());
			purchaseItem.setPurchaseId(purchaseId);
			//purchaseItem.setModel(purchaseItemDTO.getModel());
			purchaseItem.setPricePerPc(purchaseItemDTO.getPricePerUnit());
			purchaseItem.setProductTypeText(purchaseItemDTO.getProductId().toString());
			purchaseItem.setQuantity(purchaseItemDTO.getQuantity());
			purchaseItem.setSize(purchaseItemDTO.getSize());
			purchaseItem.setSrNo(purchaseItemDTO.getSrNo());
			purchaseItem.setTotalPrice(purchaseItemDTO.getTotal());
			purchaseItemList.add(purchaseItem);
		});
		purchaseItemRepository.save(purchaseItemList);
	}

	

	private void setTotalAdnDiscount(Purchase purchase, PurchaseDTO entity) {
		List<PurchaseItem> set = purchaseItemRepository.findByPurchaseId(purchase.getId()); // purchase.getPurchaseItemSet();
		Double totalOfAllItems = new Double(0);
		Double discountedAmount = new Double(0);
		if(set != null && set.size() > 0){
			for(PurchaseItem purchaseItem : set) {
				totalOfAllItems = totalOfAllItems + purchaseItem.getTotalPrice();
			}
			if(entity.getDiscountType() != null ) {
				if(entity.getDiscountType().equals("%"))
					discountedAmount = (entity.getDiscount() / 100);
				else
					discountedAmount = entity.getDiscount();
			}
		}
		
		purchase.setDiscount(entity.getDiscount());
		purchase.setBillTotal(totalOfAllItems);
		purchase.setDiscountedAmount(discountedAmount);
		purchase.setNetTotal(totalOfAllItems - discountedAmount);
		
	}

	public List<Purchase> saveAll(List<Purchase> entities) {
		return purchaseRepository.save(entities);
	}
	
	public void flush() {
		purchaseRepository.flush();
	}
	
	public void deleteById(Long id) {
		purchaseRepository.delete(id);
	}
	
	public void delete(Purchase entity) {
		purchaseRepository.delete(entity);
	}
	
	public void deleteAll(List<Purchase> entities) {
		purchaseRepository.delete(entities);
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
		Purchase purchase = purchaseRepository.findOne(id);
		Purchase p = purchase;
		if(purchase != null && p != null) {
			purchaseDTO = getDTOFromModel(p);
		}
		
		
		return purchaseDTO;
	}
	
	public boolean existsById(Long id) {
		return purchaseRepository.exists(id);
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
			purchaseDTO.setDiscountedAmount(Utils.formatDecimals(p.getDiscountedAmount()));
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
			
			List<PurchaseItem> purchaseItemList = purchaseItemRepository.findByPurchaseId(purchaseDTO.getId());
			
			if(!CollectionUtils.isEmpty(purchaseItemList)) {
				purchaseItemList.stream().forEach(pi -> {
					PurchaseItemDTO dto = new PurchaseItemDTO();
					dto.setId(pi.getId());
					dto.setItemCode(pi.getItemCode());
					dto.setMargin(pi.getMargin());
					dto.setMarginType(pi.getMarginType());
					dto.setPricePerUnit(pi.getPricePerPc());
					if(pi.getProductTypeText() != null) {
						dto.setProductId(ProductTypeEnum.getById(Long.parseLong(pi.getProductTypeText())).id);
						dto.setProductType(ProductTypeEnum.getById(dto.getProductId().longValue()).code);
					}
					dto.setQuantity(pi.getQuantity());
					Double salePrice = 0.0;
					if(pi.getMarginType() != null && pi.getMarginType().equals("%")){
						salePrice = dto.getPricePerUnit() + (dto.getPricePerUnit() * dto.getMargin() / 100);
					}else {
						salePrice = dto.getPricePerUnit() + dto.getMargin();
					}
					if(!StringUtils.isEmpty(pi.getSize())){
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


	@Override
	public void deletePurchaseItems(List<PurchaseItemDTO> deletedPurchaseItems) {
		if(CollectionUtils.isEmpty(deletedPurchaseItems)) return;
		for(PurchaseItemDTO purchaseItemDTO : deletedPurchaseItems) {
			if(purchaseItemDTO.getId() == null || purchaseItemDTO.getId().longValue() == 0)
				continue;
			purchaseItemRepository.delete(purchaseItemDTO.getId());
		}
		
	}
	
}
