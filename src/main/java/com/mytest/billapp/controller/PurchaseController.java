package com.mytest.billapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.service.ProductService;
import com.mytest.billapp.service.PurchaseService;
import com.mytest.billapp.service.VendorService;

@Controller
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	ProductService productService;
	
	private List<PurchaseItemDTO> purchaseItems;
	
	@RequestMapping(value = "purchaseList", method = RequestMethod.POST)
	public String getAllPurchase(Model model) {
		model.addAttribute("PurchaseList", purchaseService.findAll());
		model.addAttribute("purchase", new Purchase());
		model.addAttribute("selectedId","");
	    return "purchaseList";
	}
	
	
	
	@RequestMapping(value = "addPurchaseItem", method = RequestMethod.POST)
	public String addPurchaseItem(@ModelAttribute PurchaseDTO purchaseDTO, Model model) {
		PurchaseItemDTO purchaseItemDTO = new PurchaseItemDTO(purchaseDTO.getPurchaseItemDTO());
		if(CollectionUtils.isEmpty(purchaseItems))
			purchaseItems = new ArrayList<PurchaseItemDTO>();
		purchaseItems.add(purchaseItemDTO);
		PurchaseItemDTO nextPurchaseItemDTO = new PurchaseItemDTO();
		nextPurchaseItemDTO.setSrNo((purchaseItems.size()+1)+"");
		purchaseDTO.setPurchaseItemDTO(nextPurchaseItemDTO);
		setTotals(purchaseDTO, purchaseItems);
		model.addAttribute("purchase", purchaseDTO);
		model.addAttribute("purchaseItems", purchaseItems);
		addDataToModel(model);
		return "purchase";
	}
	
	
	private void setTotals(PurchaseDTO purchaseDTO, List<PurchaseItemDTO> purchaseItems) {
		Double total = new Double(0.0);
		Double discount = new Double(0.0);
		for(PurchaseItemDTO purchaseItemDTO : purchaseItems) {
			total = total + purchaseItemDTO.getTotal();
		}
		purchaseDTO.setBillTotal(total);
		if(purchaseDTO.getId() > 0) {
			purchaseDTO.setDiscount(discount);
			purchaseDTO.setNetTotal(discount);
		}else {
			if(!StringUtils.isEmpty(purchaseDTO.getDiscountType())){
				if(purchaseDTO.getDiscountType().equals("%")){
					discount = total - (total * purchaseDTO.getDiscount() / 100);
				}else {
					discount = total - purchaseDTO.getDiscount();
				}
				purchaseDTO.setDiscount(discount);
				purchaseDTO.setNetTotal(total - discount);
			}
		}
	}



	private void addDataToModel(Model model) {
		model.addAttribute("purchaseList", purchaseService.findAll());
		model.addAttribute("productTypeList", productService.getProductTypes());
		model.addAttribute("vendorList", vendorService.findAll());
		model.addAttribute("selectedId","");
		model.addAttribute("message", "");
	}



	@RequestMapping(value = "savePurchase", method = RequestMethod.POST)
	public String savePurchase(@ModelAttribute PurchaseDTO purchaseDTO, Model model) {
		try {
			if(purchaseItems == null || purchaseItems.size() == 0)
			{
				model.addAttribute("message", "No items found to save this purchase");
			}else {
				purchaseDTO.setPurchaseItems(purchaseItems);
				PurchaseDTO dto = purchaseService.save(purchaseDTO);
				purchaseDTO = new PurchaseDTO();
				purchaseDTO.setPurchaseItemDTO(new PurchaseItemDTO());
				purchaseItems = new ArrayList<PurchaseItemDTO>();
				model.addAttribute("PurchaseList", purchaseService.findAll());
				//model.addAttribute("purchase", new Purchase());
				model.addAttribute("selectedId","");
				model.addAttribute("message", "Succesfully Saved.");
				model.addAttribute("selectedId","");
			}
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "purchaseList";
		}
	    return "purchaseList";
	}
	
	
	@RequestMapping(value = "purchase", method = RequestMethod.POST)
	public String addPurchase(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		
		try {
			PurchaseDTO purchaseDTO = null;
			if(selectedId == null || selectedId.intValue() == 0){
				purchaseDTO = new PurchaseDTO();
				purchaseDTO.setPurchaseItemDTO(new PurchaseItemDTO());
				purchaseDTO.getPurchaseItemDTO().setSrNo("1");
				model.addAttribute("purchase", purchaseDTO);
			}else {
				purchaseDTO = purchaseService.findById(selectedId);
				model.addAttribute("purchase", purchaseDTO);
				
			}
			purchaseItems = new ArrayList<>(purchaseDTO.getPurchaseItems());
			model.addAttribute("purchaseItems", purchaseItems);
			model.addAttribute("productTypeList", productService.getProductTypes());
			model.addAttribute("vendorList", vendorService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "purchase";
		}
		return "purchase";
	}
	
	
	@RequestMapping(value = "deletePurchase", method = RequestMethod.POST)
	public String deletePurchase(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			/*Purchase purchase = purchaseService.findById(selectedId)
			        .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			purchaseService.delete(purchase);*/
	    	model.addAttribute("vendorList", vendorService.findAll());
	    	model.addAttribute("productTypeList", productService.getProductList());
			model.addAttribute("purchaseList", purchaseService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllPurchase(model);
		}
	    return addPurchase(0l, model);
	}
}
