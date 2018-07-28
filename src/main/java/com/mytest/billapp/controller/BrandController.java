package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Brand;
import com.mytest.billapp.service.BrandService;

@Controller
@RequestMapping(value="admin")
public class BrandController {
	
	@Autowired
	BrandService brandService;
	
	@RequestMapping(value = "brandList", method = RequestMethod.POST)
	public String getAllBrand(Model model) {
		model.addAttribute("brandList", brandService.findAll());
		model.addAttribute("brand", new Brand());
		model.addAttribute("selectedId","");
	    return "brand";
	}
	
	@RequestMapping(value = "saveBrand", method = RequestMethod.POST)
	public String saveBrand(@ModelAttribute Brand brand, Model model) {
		try {
			brandService.save(brand);
			model.addAttribute("brand", new Brand());
			model.addAttribute("brandList", brandService.findAll());
			model.addAttribute("message", "Succesfully Saved.");
			model.addAttribute("selectedId","");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "brand";
		}
	    return "brand";
	}
	
	
	@RequestMapping(value = "brand", method = RequestMethod.POST)
	public String addBrand(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("brand", new Brand());
		try {
			model.addAttribute("brand", brandService.findById(selectedId));
			model.addAttribute("brandList", brandService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "brand";
		}
		return "brand";
	}
	
	
	@RequestMapping(value = "deleteBrand", method = RequestMethod.POST)
	public String deleteBrand(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			Brand brand = brandService.findById(selectedId);
			      //  .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			brandService.delete(brand);
			model.addAttribute("brandList", brandService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllBrand(model);
		}
	    return addBrand(0l, model);
	}
}
