package com.mytest.billapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.service.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@GetMapping("/purchaseList")
	public String getAllPurchase(Model model) {
		model.addAttribute("purchaseList", purchaseService.findAll());
	    return "purchaseList";
	}
	
	//@PostMapping("/savePurchase")
	@RequestMapping(value = "savePurchase", method = RequestMethod.POST)
	public String savePurchase(@ModelAttribute Purchase purchase, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("purchase", purchaseService.save(purchase));
	    return "purchase";
	}
	
	@GetMapping("/purchase/{id}")
	public String getPurchaseById(@PathVariable(value = "id") Long id, Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("purchase", new Purchase());
		model.addAttribute("purchase", purchaseService.findById(id));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "purchase";
	}
	
	@PutMapping("/purchase/update/{id}")
	public String updatePurchase(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute Purchase purchase, Model model) {

	    Purchase purchase1 = purchaseService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    //purchase1.setTitle(purchase.getTitle());
	    //purchase1.setContent(purchase.getContent());
	    model.addAttribute("purchase",purchaseService.save(purchase1));
	    return "purchase";
	}
	
	@GetMapping("/purchase/delete/{id}")
	public String deletePurchase(@PathVariable(value = "id") Long id, Model model) {
	    Purchase purchase = purchaseService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    purchaseService.delete(purchase);
	    return getAllPurchase(model);
	}
}
