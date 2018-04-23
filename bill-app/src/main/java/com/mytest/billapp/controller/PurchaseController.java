package com.mytest.billapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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
		if(CollectionUtils.isEmpty(purchaseDTO.getPurchaseItems()))
			purchaseDTO.setPurchaseItems(new ArrayList<PurchaseItemDTO>());
		purchaseDTO.getPurchaseItems().add(purchaseItemDTO);
		purchaseDTO.setPurchaseItemDTO(new PurchaseItemDTO());
		model.addAttribute("purchase", purchaseDTO);
		model.addAttribute("purchaseItems", purchaseDTO.getPurchaseItems());
		addDataToModel(model);
		return "purchase";
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
			
			
			
			
			
			
			
			
			
			
			PurchaseDTO dto = purchaseService.save(purchaseDTO);
			purchaseDTO = new PurchaseDTO();
			purchaseDTO.setPurchaseItemDTO(new PurchaseItemDTO());
			purchaseDTO.getPurchaseItemDTO().setSrNo(dto.getPurchaseItems().size()+1+"");
			model.addAttribute("purchase", purchaseDTO);
			model.addAttribute("purchaseList", purchaseService.findAll());
			model.addAttribute("vendorList", vendorService.findAll());
			model.addAttribute("productTypeList", productService.getProductList());
			model.addAttribute("message", "Succesfully Saved.");
			model.addAttribute("selectedId","");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "purchase";
		}
	    return "purchase";
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
			model.addAttribute("purchaseList", purchaseService.findAll());
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
