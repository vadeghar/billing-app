package com.mytest.billapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="admin/dashboard1")
public class Dashboard1Controller {

	@RequestMapping(value = "home")
	public String getHome(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Dashboard");
		model.addAttribute("breadcrubms",breadcrubms);
		System.out.println("@@@breadcrubms "+breadcrubms);
		model.addAttribute("activeMenuItem", "");
		model.addAttribute("breadcrubmsHeading", "Dashboard");
	    return "dashboard1Home";
	}
	
	@RequestMapping(value = "brands")
	public String getAllBrand(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Invoice - Master");
		breadcrubms.add("Brands");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemBrands");
		model.addAttribute("breadcrubmsHeading", "Brands");
	    return "dashboard1Brand";
	}
	
	@RequestMapping(value = "suppliers")
	public String getAllSuppliers(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Invoice - Master");
		breadcrubms.add("Suppliers");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemSuppliers");
		model.addAttribute("breadcrubmsHeading", "Suppliers");
	    return "dashboard1Supplier";
	}
	
	@RequestMapping(value = "permissions")
	public String getAllPermissions(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Application - Master");
		breadcrubms.add("Permissions");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemPermissions");
		model.addAttribute("breadcrubmsHeading", "Permissions");
	    return "dashboard1Permissions";
	}
	
	@RequestMapping(value = "roles")
	public String getRoles(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Application - Master");
		breadcrubms.add("Roles");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemRoles");
		model.addAttribute("breadcrubmsHeading", "Role");
	    return "dashboard1Role";
	}
	
	@RequestMapping(value = "users")
	public String getUsers(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Application - Master");
		breadcrubms.add("User");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemUsers");
		model.addAttribute("breadcrubmsHeading", "Users");
	    return "dashboard1User";
	}
	
	@RequestMapping(value = "customers")
	public String getCustomer(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Application - Master");
		breadcrubms.add("Customers");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemCustomers");
		model.addAttribute("breadcrubmsHeading", "Customers");
	    return "dashboard1Customer";
	}
	
	@RequestMapping(value = "jewelCategory")
	public String getAllJewelCategory(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Invoice - Master");
		breadcrubms.add("Jewel Category");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemJewelCategory");
		model.addAttribute("breadcrubmsHeading", "Jewel-Category");
	    return "dashboard1JewelCategory";
	}
	
	@RequestMapping(value = "purchaseEntry")
	public String getPurchaseEntry(Model model) {
		List<String> breadcrubms = new ArrayList<String>();
		breadcrubms.add("Home");
		breadcrubms.add("Invoice - Master");
		breadcrubms.add("Pruchase Entry");
		model.addAttribute("breadcrubms",breadcrubms);
		model.addAttribute("activeMenuItem", "menuItemPurchaseEntry");
		model.addAttribute("breadcrubmsHeading", "Purchase Entry");
	    return "dashboard1PurchaseEntry";
	}
}
