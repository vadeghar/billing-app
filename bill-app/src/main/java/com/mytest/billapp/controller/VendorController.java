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
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.service.VendorService;

@Controller
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@GetMapping("/vendorList")
	public String getAllVendor(Model model) {
		model.addAttribute("vendorList", vendorService.findAll());
	    return "vendorList";
	}
	
	//@PostMapping("/saveVendor")
	@RequestMapping(value = "saveVendor", method = RequestMethod.POST)
	public String saveVendor(@ModelAttribute Vendor vendor, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("vendor", vendorService.save(vendor));
	    return "vendor";
	}
	
	@GetMapping("/vendor/{id}")
	public String getVendorById(@PathVariable(value = "id") Long id, Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("vendor", new Vendor());
		model.addAttribute("vendor", vendorService.findById(id));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "vendor";
	}
	
	@PutMapping("/vendor/update/{id}")
	public String updateVendor(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute Vendor vendor, Model model) {

	    Vendor vendor1 = vendorService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    //vendor1.setTitle(vendor.getTitle());
	    //vendor1.setContent(vendor.getContent());
	    model.addAttribute("vendor",vendorService.save(vendor1));
	    return "vendor";
	}
	
	@GetMapping("/vendor/delete/{id}")
	public String deleteVendor(@PathVariable(value = "id") Long id, Model model) {
	    Vendor vendor = vendorService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    vendorService.delete(vendor);
	    return getAllVendor(model);
	}
}
