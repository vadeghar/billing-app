package com.mytest.billapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mysql.jdbc.StringUtils;
import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.model.PurchaseItem;
import com.mytest.billapp.model.Stock;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.PurchaseRepository;
import com.mytest.billapp.repsitory.StockRepository;
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
	
	@Autowired
	StockRepository stockRepository;
	
	public PurchaseDTO save(PurchaseDTO entity) {
		try {
			Purchase purchase = null;
			List<PurchaseItemDTO> newItemsAdded = new ArrayList<PurchaseItemDTO>();
			if(entity.getId() != null && entity.getId().longValue() > 0) {
				purchase = purchaseRepository.findById(entity.getId()).get();
			}else {
				purchase = new Purchase();
			}
			if(!CollectionUtils.isEmpty(entity.getPurchaseItems())) {
				for(PurchaseItemDTO itemDTO : entity.getPurchaseItems()) {
					if(itemDTO.getId() == null || itemDTO.getId().longValue() == 0)
						newItemsAdded.add(itemDTO);
					updatePurchaseItem(purchase, itemDTO);
				}
			}
			purchase.setBillDate(entity.getBillDate() != null ? sdf.parse(entity.getBillDate()) : null);
			purchase.setBillNo(entity.getBillNo());
			purchase.setDiscountType(entity.getDiscountType());
			purchase.setVendor(vendorRepository.getOne(entity.getVendorId()));
			//purchase.setPurchaseItemSet(purchaseItemSet);
			setTotalAdnDiscount(purchase, entity);
			
			Purchase p =purchaseRepository.save(purchase);
			PurchaseDTO dbPurchaseDTO = findById(p.getId());
			updateSrockDetails(newItemsAdded);
			return dbPurchaseDTO;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return purchaseRepository.save(entity);
		
		return null;
	}
	
	private void updateSrockDetails(List<PurchaseItemDTO> newItemsAdded) {
		if(!CollectionUtils.isEmpty(newItemsAdded)) {
			for(PurchaseItemDTO purchaseItemDto : newItemsAdded) {
				Stock stock = stockRepository.findByItemCode(purchaseItemDto.getItemCode());
				if(stock == null)
					stock = new Stock();
				stock.setItemCode(purchaseItemDto.getItemCode());
				Integer oldQty = stock.getQuantity();
				Integer updatedQty = oldQty != null ? oldQty +  purchaseItemDto.getQuantity() : purchaseItemDto.getQuantity();
				stock.setQuantity(updatedQty);
				stock.setSalePricePerPc(purchaseItemDto.getSalePrice());
				stockRepository.save(stock);
			}
		}
	}

	
	private void updatePurchaseItem(Purchase purchase, PurchaseItemDTO itemDTO) {
		if(itemDTO.getId() != null && itemDTO.getId() > 0) {
			// Existing Purchase Item
			if(purchase.getPurchaseItemSet() != null) {
				for(PurchaseItem dbPurchaseItem : purchase.getPurchaseItemSet()) {
					if(dbPurchaseItem.getId().longValue() == itemDTO.getId().longValue()) {
						dbPurchaseItem.setId(itemDTO.getId());
						dbPurchaseItem.setItemCode(itemDTO.getItemCode());
						dbPurchaseItem.setMargin(itemDTO.getMargin());
						dbPurchaseItem.setMarginType(itemDTO.getMarginType());
						//dbPurchaseItem.setModel(itemDTO.getModel());
						dbPurchaseItem.setPricePerPc(itemDTO.getPricePerUnit());
						dbPurchaseItem.setProductTypeText(itemDTO.getProductId().toString());
						dbPurchaseItem.setQuantity(itemDTO.getQuantity());
						dbPurchaseItem.setSize(itemDTO.getSize());
						dbPurchaseItem.setSrNo(itemDTO.getSrNo());
						dbPurchaseItem.setTotalPrice(itemDTO.getTotal());
						//purchase.addPurhcaseItem(purchaseItem);
					}
				}
			}
			
		} else {
			// New Purchase Item
			PurchaseItem purchaseItem = new PurchaseItem();
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
		}
	}

	private void setTotalAdnDiscount(Purchase purchase, PurchaseDTO entity) {
		Set<PurchaseItem> set = purchase.getPurchaseItemSet();
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
			
			if(p.getPurchaseItemSet().size() > 0) {
				p.getPurchaseItemSet().stream().forEach(pi -> {
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
