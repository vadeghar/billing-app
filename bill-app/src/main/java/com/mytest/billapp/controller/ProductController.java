package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "productList", method = RequestMethod.POST)
	public String getAllProduct(Model model) {
		model.addAttribute("productList", productService.findAll());
		model.addAttribute("product", new Product());
		model.addAttribute("selectedId","");
	    return "product";
	}
	
	@RequestMapping(value = "saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute Product product, Model model) {
		try {
			productService.save(product);
			model.addAttribute("product", new Product());
			model.addAttribute("productList", productService.findAll());
			model.addAttribute("message", "Succesfully Saved.");
			model.addAttribute("selectedId","");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
	    return "product";
	}
	
	
	@RequestMapping(value = "product", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("product", new Product());
		try {
			model.addAttribute("product", productService.findById(selectedId));
			model.addAttribute("productList", productService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
		return "product";
	}
	
	
	@RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			Product product = productService.findById(selectedId)
			        .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			productService.delete(product);
			model.addAttribute("productList", productService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllProduct(model);
		}
	    return addProduct(0l, model);
	}
}
