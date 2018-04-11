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
import com.mytest.billapp.model.Brand;
import com.mytest.billapp.service.BrandService;

@Controller
public class BrandController {
	
	@Autowired
	BrandService brandService;
	
	@GetMapping("/brandList")
	public String getAllBrand(Model model) {
		model.addAttribute("brandList", brandService.findAll());
	    return "brandList";
	}
	
	//@PostMapping("/saveBrand")
	@RequestMapping(value = "saveBrand", method = RequestMethod.POST)
	public String saveBrand(@ModelAttribute Brand brand, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("brand", brandService.save(brand));
	    return "brand";
	}
	
	@GetMapping("/brand/{id}")
	public String getBrandById(@PathVariable(value = "id") Long id, Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("brand", new Brand());
		model.addAttribute("brand", brandService.findById(id));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "brand";
	}
	
	@PutMapping("/brand/update/{id}")
	public String updateBrand(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute Brand brand, Model model) {

	    Brand brand1 = brandService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    //brand1.setTitle(brand.getTitle());
	    //brand1.setContent(brand.getContent());
	    model.addAttribute("brand",brandService.save(brand1));
	    return "brand";
	}
	
	@GetMapping("/brand/delete/{id}")
	public String deleteBrand(@PathVariable(value = "id") Long id, Model model) {
	    Brand brand = brandService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    brandService.delete(brand);
	    return getAllBrand(model);
	}
}
