package com.mytest.billapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="admin/dashboard1")
public class Dashboard1Controller {

	@RequestMapping(value = "home")
	public String getAllBrand(Model model) {
	    return "dashboard1Home";
	}
}
