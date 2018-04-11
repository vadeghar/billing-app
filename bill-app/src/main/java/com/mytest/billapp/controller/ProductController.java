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
import com.mytest.billapp.model.Product;
import com.mytest.billapp.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/productList")
	public String getAllProduct(Model model) {
		model.addAttribute("productList", productService.findAll());
	    return "productList";
	}
	
	//@PostMapping("/saveProduct")
	@RequestMapping(value = "saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute Product product, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("product", productService.save(product));
	    return "product";
	}
	
	@GetMapping("/product/{id}")
	public String getProductById(@PathVariable(value = "id") Long id, Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("product", new Product());
		model.addAttribute("product", productService.findById(id));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "product";
	}
	
	@PutMapping("/product/update/{id}")
	public String updateProduct(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute Product product, Model model) {

	    Product product1 = productService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    //product1.setTitle(product.getTitle());
	    //product1.setContent(product.getContent());
	    model.addAttribute("product",productService.save(product1));
	    return "product";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id") Long id, Model model) {
	    Product product = productService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    productService.delete(product);
	    return getAllProduct(model);
	}
}
