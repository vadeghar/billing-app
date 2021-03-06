package com.mytest.billapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.model.ProductItems;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.model.PurchaseItem;
import com.mytest.billapp.model.Stock;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.ProductItemsRepository;
import com.mytest.billapp.repsitory.ProductRepository;
import com.mytest.billapp.repsitory.PurchaseItemRepository;
import com.mytest.billapp.repsitory.PurchaseRepository;
import com.mytest.billapp.repsitory.StockRepository;
import com.mytest.billapp.repsitory.VendorRepository;
import com.mytest.billapp.service.PurchaseService;
import com.mytest.billapp.utils.AppUtils;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	PurchaseRepository purchaseRepository; 
	@Autowired
	ProductRepository productRepository;
	@Autowired
	PurchaseItemRepository purchaseItemRepository; 
	@Autowired
	ProductItemsRepository productItemsRepository;
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	public PurchaseDTO saveOrUpatePurchase(PurchaseDTO entity) {
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
			/*if(!CollectionUtils.isEmpty(entity.getPurchaseItems())) {
				saveAllPurchaseItem(entity.getPurchaseItems(), p.getId());
			}*/
			
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
			purchaseItem.setProductItemId(purchaseItemDTO.getProductItemId());
			//purchaseItem.setModel(purchaseItemDTO.getModel());
			purchaseItem.setPricePerPc(purchaseItemDTO.getPricePerUnit());
			purchaseItem.setQuantity(purchaseItemDTO.getQuantity());
			//purchaseItem.setSize(purchaseItemDTO.getSize());
			purchaseItem.setSrNo(purchaseItemDTO.getSrNo());
			purchaseItem.setTotalPrice(purchaseItemDTO.getTotal());
			purchaseItemList.add(purchaseItem);
		});
		purchaseItemRepository.save(purchaseItemList);
	}
	
	public void savePurchaseItemAndStock(PurchaseItemDTO purchaseItemDTO, Long purchaseId) {

			List<PurchaseItem> purchaseItems = purchaseItemRepository.findByItemCode(purchaseItemDTO.getItemCode());
			Stock stock = stockRepository.findByItemCode(purchaseItemDTO.getItemCode());
			PurchaseItem purchaseItem = null;
			Integer existCount = new Integer(0);
			if(!CollectionUtils.isEmpty(purchaseItems)) {
				purchaseItem = purchaseItems.get(0);
				if(purchaseItemDTO.getId() == null || purchaseItemDTO.getId().intValue()  == 0)
					existCount = purchaseItem.getQuantity();
			}else {
				purchaseItem = new PurchaseItem();
			}
			
			if(stock == null) {
				stock = new Stock();
				stock.setItemCode(purchaseItemDTO.getItemCode());
				stock.setQuantity(purchaseItemDTO.getQuantity());
				stock.setSalePricePerPc(purchaseItemDTO.getSalePrice());
			} else {
				if(purchaseItemDTO.getId() == null || purchaseItemDTO.getId().intValue()  == 0) {
					int existingStock = stock.getQuantity();
					stock.setQuantity(purchaseItemDTO.getQuantity() + existingStock);
				}else {
					stock.setQuantity(purchaseItemDTO.getQuantity());
				}
			}
			
			/*if(purchaseItemDTO.getId() == null || purchaseItemDTO.getId().longValue() == 0) 
				purchaseItem = new PurchaseItem();
			else 
				purchaseItem = purchaseItemRepository.getOne(purchaseItemDTO.getId());*/
			
			purchaseItem.setItemCode(purchaseItemDTO.getItemCode());
			purchaseItem.setMargin(purchaseItemDTO.getMargin());
			purchaseItem.setMarginType(purchaseItemDTO.getMarginType());
			purchaseItem.setPurchaseId(purchaseId);
			purchaseItem.setProductItemId(purchaseItemDTO.getProductItemId());
			purchaseItem.setPricePerPc(purchaseItemDTO.getPricePerUnit());
			purchaseItem.setQuantity(purchaseItemDTO.getQuantity() + existCount);
			purchaseItem.setSrNo(purchaseItemDTO.getSrNo());
			purchaseItem.setTotalPrice(purchaseItemDTO.getTotal());
			purchaseItemRepository.save(purchaseItem);
			stockRepository.save(stock);
	}

	public void deletePurchaseItemUpdateStock(PurchaseItem purchaseItem) {
		Stock stock = stockRepository.findByItemCode(purchaseItem.getItemCode());
		if(stock != null) {
			Integer qntyAvbl = stock.getQuantity();
			qntyAvbl = qntyAvbl - purchaseItem.getQuantity();
			if(qntyAvbl > 0) {
				stock.setQuantity(qntyAvbl);
				stockRepository.save(stock);
			}else {
				stockRepository.delete(stock);
			}
		}
		purchaseItemRepository.delete(purchaseItem);
	}

	public void updatePurchaseTotals(PurchaseDTO entity) {
		Purchase purchase = purchaseRepository.findOne(entity.getId());
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
		purchaseRepository.save(purchase);
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
			purchaseDTO.setBillTotal(AppUtils.formatDecimals(p.getBillTotal()));
			purchaseDTO.setDiscount(AppUtils.formatDecimals(p.getDiscount()));
			purchaseDTO.setDiscountedAmount(AppUtils.formatDecimals(p.getDiscountedAmount()));
			purchaseDTO.setDiscountType(p.getDiscountType());
			purchaseDTO.setEntryDate(sdf.format(p.getEntryDate()));
			purchaseDTO.setNetTotal(AppUtils.formatDecimals(p.getNetTotal()));
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
					PurchaseItemDTO dto = convertModelToView(pi);
					purchaseDTO.getPurchaseItems().add(dto);
				});
			}
			
		}
		return purchaseDTO;
	}
	
	
	public PurchaseItemDTO convertModelToView(PurchaseItem pi) {
		PurchaseItemDTO dto = new PurchaseItemDTO();
		dto.setId(pi.getId());
		dto.setPurchaseId(pi.getPurchaseId());
		dto.setItemCode(pi.getItemCode());
		dto.setMargin(pi.getMargin());
		dto.setMarginType(pi.getMarginType());
		dto.setPricePerUnit(pi.getPricePerPc());
		dto.setQuantity(pi.getQuantity());
		ProductItems productItem = productItemsRepository.getOne(pi.getProductItemId());
		dto.setProductItemId(productItem.getId());
		dto.setProductItemName(productItem.getName());
		dto.setProductId(productItem.getProductId());
		Product product = productRepository.getOne(productItem.getProductId());
		dto.setProductName(product.getName());
		Double salePrice = 0.0;
		if(pi.getMarginType() != null && pi.getMarginType().equals("%")){
			salePrice = dto.getPricePerUnit() + (dto.getPricePerUnit() * dto.getMargin() / 100);
		}else {
			salePrice = dto.getPricePerUnit() + dto.getMargin();
		}
		dto.setSalePrice(salePrice);
		dto.setSrNo(pi.getSrNo());
		dto.setTotal(pi.getTotalPrice());
		return dto;
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
