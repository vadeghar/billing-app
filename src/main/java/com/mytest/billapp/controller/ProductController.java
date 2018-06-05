package com.mytest.billapp.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.model.Brand;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.model.ProductItems;
import com.mytest.billapp.repsitory.ProductItemsRepository;
import com.mytest.billapp.service.BrandService;
import com.mytest.billapp.service.ProductItemsService;
import com.mytest.billapp.service.ProductService;
import com.mytest.billapp.view.ProductView;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	@Autowired
	ProductItemsService productItemsService;
	
	@Autowired
	BrandService brandService;
	
	private ProductView productView;
	
	private void init() {
		productView = new ProductView();
	}
	
	@RequestMapping(value = "productList", method = RequestMethod.POST)
	public String getAllProduct(Model model) {
		addModelData(model);
		model.addAttribute("productView",productView);
	    return "product";
	}
	
	@RequestMapping(value = "saveBrandInProduct", method = RequestMethod.POST)
	public String saveBrandInProduct(@ModelAttribute("newBrnadName") String newBrnadName, @ModelAttribute Product product, Model model) {
		try {
			
			if(!StringUtils.isEmpty(newBrnadName)) {
				Brand brand = new Brand();
				brand.setName(newBrnadName);
				brandService.save(brand);
				addModelData(model);
				productView.setMessage("Brand Saved");
			}
			model.addAttribute("productView",productView);
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
		return "product";
	}
	
	@RequestMapping(value = "saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute ProductView productView, Model model) {
		try {
			productService.save(productView.getProduct());
			addModelData(model);
			this.productView.setMessage("Succesfully Saved.");
			model.addAttribute("productView",this.productView);
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
	    return "product";
	}
	
	
	//
	@RequestMapping(value = "saveProductItem", method = RequestMethod.POST)
	public String saveProductItem(@ModelAttribute ProductView productView, Model model) {

		if(productView == null) productView = new ProductView();
		if(productView.getProductItems() != null) {
			productView.getProductItems().setProductId(productView.getProduct().getId());
			productItemsService.save(productView.getProductItems());
		}
		//productView.setBrandList(brandService.findAll());
		//productView.setProductList(setBrandOnAllProducts(productService.findAll()));
		/*Product product = productService.findById(selectedId);
		productView.setProduct(product);
		productView.setBrandName(brandService.findById(product.getBrandId()).getName());*/
		productView.setProductItems(new ProductItems());
		productView.setProductItemsList( productItemsService.findAllByProductId(productView.getProduct().getId()));
		productView.setSelectedId(0l);
		productView.setSelectedItemId(0l);
		productView.setNewBrnadName(StringUtils.EMPTY);
		productView.setMessage("Item saved successfully");
		productView.setFeedback(StringUtils.EMPTY);
		
		model.addAttribute("productView",productView);
		
		
		return "productItems";
	}
	
	@RequestMapping(value = "deleteProductItems", method = RequestMethod.POST)
	public String deleteProductItems(@ModelAttribute("selectedId") Long selectedId, Model model) {

		if(productView == null) productView = new ProductView();
		productItemsService.deleteById(selectedId);
		productView.setProductItems(new ProductItems());
		productView.setProductItemsList( productItemsService.findAllByProductId(productView.getProduct().getId()));
		productView.setSelectedId(0l);
		productView.setSelectedItemId(0l);
		productView.setNewBrnadName(StringUtils.EMPTY);
		productView.setMessage("Sucessfully Deleted");
		productView.setFeedback(StringUtils.EMPTY);
		
		model.addAttribute("productView",productView);
		
		
		return "productItems";
	}
	
	@RequestMapping(value = "editProductItem", method = RequestMethod.POST)
	public String editProductItem(@ModelAttribute("selectedId") Long selectedId, Model model) {

		if(productView == null) productView = new ProductView();
		if(productView.getProductItems() != null)
			productView.setProductItems(productItemsService.findById(selectedId));
		else
			productView.setProductItems(new ProductItems());
		productView.setProductItemsList( productItemsService.findAllByProductId(productView.getProduct().getId()));
		productView.setSelectedId(0l);
		productView.setSelectedItemId(0l);
		productView.setNewBrnadName(StringUtils.EMPTY);
		productView.setMessage(StringUtils.EMPTY);
		productView.setFeedback(StringUtils.EMPTY);
		
		model.addAttribute("productView",productView);
		
		
		return "productItems";
	}
	
	
	
	@RequestMapping(value = "product", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		addModelData(model);
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("product", new Product());
		try {
			productView.setProduct(productService.findById(selectedId));
			model.addAttribute("productView",productView);
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
		return "product";
	}
	
	/*
	@RequestMapping(value = "productItems", method = RequestMethod.POST)
	public String productItems(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("product", new Product());
		try {
			addModelData(model);
			productView.setProduct(productService.findById(selectedId));
			model.addAttribute("productView",productView);
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
		return "product";
	}
	*/
	@RequestMapping(value = "productItems", method = RequestMethod.POST)
	public String productItems(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		try {
			if(productView == null) productView = new ProductView();
				//productView.setBrandList(brandService.findAll());
				//productView.setProductList(setBrandOnAllProducts(productService.findAll()));
				Product product = productService.findById(selectedId);
				productView.setProduct(product);
				productView.setBrandName(brandService.findById(product.getBrandId()).getName());
				productView.setProductItemsList( productItemsService.findAllByProductId(selectedId));
				productView.setProductItems(new ProductItems());
				productView.setSelectedId(0l);
				productView.setSelectedItemId(0l);
				productView.setNewBrnadName(StringUtils.EMPTY);
				productView.setMessage(StringUtils.EMPTY);
				productView.setFeedback(StringUtils.EMPTY);
				
				model.addAttribute("productView",productView);
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "product";
		}
		return "productItems";
	}
	
	
	@RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			Product product = productService.findById(selectedId);
			List<ProductItems> productItems =  productItemsService.findAllByProductId(product.getId());
			productItemsService.deleteAll(productItems);
			productService.delete(product);
			addModelData(model);
			productView.setMessage("Succesfully Deleted.");
			model.addAttribute("productView",productView);
		} catch (Exception e) {
			e.printStackTrace();
			addModelData(model);
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			 return "product";
		}
	    return "product";
	}
	
	private void addModelData(Model model) {
		init();
		
		productView.setBrandList(brandService.findAll());
		productView.setProductList(setBrandOnAllProducts(productService.findAll()));
		productView.setProduct(new Product());
		productView.setSelectedId(0l);
		productView.setSelectedItemId(0l);
		productView.setNewBrnadName(StringUtils.EMPTY);
		productView.setMessage(StringUtils.EMPTY);
		productView.setFeedback(StringUtils.EMPTY);
		
		model.addAttribute("productList", setBrandOnAllProducts(productService.findAll()));
		//model.addAttribute("brandList", brandService.findAll());
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
