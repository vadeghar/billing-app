package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.service.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@RequestMapping(value = "purchaseList", method = RequestMethod.POST)
	public String getAllPurchase(Model model) {
		model.addAttribute("purchaseList", purchaseService.findAll());
		model.addAttribute("purchase", new Purchase());
		model.addAttribute("selectedId","");
	    return "purchase";
	}
	
	@RequestMapping(value = "savePurchase", method = RequestMethod.POST)
	public String savePurchase(@ModelAttribute Purchase purchase, Model model) {
		try {
			purchaseService.save(purchase);
			model.addAttribute("purchase", new Purchase());
			model.addAttribute("purchaseList", purchaseService.findAll());
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
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("purchase", new Purchase());
		try {
			model.addAttribute("purchase", purchaseService.findById(selectedId));
			model.addAttribute("purchaseList", purchaseService.findAll());
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
			Purchase purchase = purchaseService.findById(selectedId)
			        .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			purchaseService.delete(purchase);
			model.addAttribute("purchaseList", purchaseService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllPurchase(model);
		}
	    return addPurchase(0l, model);
	}
}
