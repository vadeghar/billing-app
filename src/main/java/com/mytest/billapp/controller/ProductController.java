package com.mytest.billapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.model.Brand;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.service.BrandService;
import com.mytest.billapp.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandService brandService;
	
	@RequestMapping(value = "productList", method = RequestMethod.POST)
	public String getAllProduct(Model model) {
		addModelData(model);
		/*model.addAttribute("brandList", brandService.findAll());
		model.addAttribute("productList", setBrandOnAllProducts(productService.findAll()));
		model.addAttribute("product", new Product());
		model.addAttribute("selectedId","");
		model.addAttribute("newBrnadName","");*/
	    return "product";
	}
	
	@RequestMapping(value = "saveBrandInProduct", method = RequestMethod.POST)
	public String saveBrandInProduct(@ModelAttribute("newBrnadName") String newBrnadName, @ModelAttribute Product product, Model model) {
		try {
			addModelData(model);
			if(!StringUtils.isEmpty(newBrnadName)) {
				Brand brand = new Brand();
				brand.setName(newBrnadName);
				brandService.save(brand);
				model.addAttribute("message", "Brand Saved");
			}
			model.addAttribute("product", product);
			
			/*model.addAttribute("brandList", brandService.findAll());
			model.addAttribute("productList", setBrandOnAllProducts(productService.findAll()));
			model.addAttribute("selectedId","");
			model.addAttribute("newBrnadName","");*/
			
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
		return "product";
	}
	
	@RequestMapping(value = "saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute Product product, Model model) {
		try {
			System.out.println("Brand ID @@@@@@@@@@@@ "+product.getBrandId());
			/*if(product.getBrand() == null)
				product.setBrand(new Brand());*/
			productService.save(product);
			addModelData(model);
			model.addAttribute("message", "Succesfully Saved.");
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
			addModelData(model);
			model.addAttribute("product", productService.findById(selectedId));
			/*model.addAttribute("productList", setBrandOnAllProducts(productService.findAll()));
			model.addAttribute("brandList", brandService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
			model.addAttribute("newBrnadName","");*/
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
			Product product = productService.findById(selectedId);
			productService.delete(product);
			addModelData(model);
			model.addAttribute("message", "Succesfully Deleted.");
		} catch (Exception e) {
			e.printStackTrace();
			addModelData(model);
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			 return "product";
		}
	    return "product";
	}
	
	private void addModelData(Model model) {
		model.addAttribute("productList", setBrandOnAllProducts(productService.findAll()));
		model.addAttribute("brandList", brandService.findAll());
		model.addAttribute("selectedId","");
		model.addAttribute("message", "");
		model.addAttribute("newBrnadName","");
		model.addAttribute("product", new Product());
	}
	
	private List<Product> setBrandOnAllProducts(List<Product> products) {
		/*products.stream().filter( p -> p.getBrandId() != null && p.getBrandId() > 0).collect(Collectors.toList()).forEach( product -> {
			product.setBrandName(brandService.getOne(product.getBrandId()).getName());
		});;*/
		
		products.stream().forEach( product -> {
			if(product.getBrandId() != null && product.getBrandId() > 0)
				product.setBrandName(brandService.getOne(product.getBrandId()).getName());
		});
		return products;
	}
}
