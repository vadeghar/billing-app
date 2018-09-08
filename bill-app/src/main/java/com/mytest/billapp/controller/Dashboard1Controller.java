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
}
