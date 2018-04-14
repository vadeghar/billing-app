package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.service.VendorService;

@Controller
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@RequestMapping(value = "vendorList", method = RequestMethod.POST)
	public String getAllVendor(Model model) {
		model.addAttribute("vendorList", vendorService.findAll());
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("selectedId","");
	    return "vendor";
	}
	
	@RequestMapping(value = "saveVendor", method = RequestMethod.POST)
	public String saveVendor(@ModelAttribute Vendor vendor, Model model) {
		try {
			vendorService.save(vendor);
			model.addAttribute("vendor", new Vendor());
			model.addAttribute("vendorList", vendorService.findAll());
			model.addAttribute("message", "Succesfully Saved.");
			model.addAttribute("selectedId","");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "vendor";
		}
	    return "vendor";
	}
	
	
	@RequestMapping(value = "vendor", method = RequestMethod.POST)
	public String addVendor(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("vendor", new Vendor());
		try {
			model.addAttribute("vendor", vendorService.findById(selectedId));
			model.addAttribute("vendorList", vendorService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "vendor";
		}
		return "vendor";
	}
	
	
	@RequestMapping(value = "deleteVendor", method = RequestMethod.POST)
	public String deleteVendor(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			Vendor vendor = vendorService.findById(selectedId)
			        .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			vendorService.delete(vendor);
			model.addAttribute("vendorList", vendorService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllVendor(model);
		}
	    return addVendor(0l, model);
	}
}
