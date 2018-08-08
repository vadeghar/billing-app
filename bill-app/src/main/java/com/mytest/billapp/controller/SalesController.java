package com.mytest.billapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.model.Sale;


@Controller
@RequestMapping(value="admin/sales")
public class SalesController {

	@RequestMapping(value = "saleEntry", method = RequestMethod.POST)
	public String getSalesPage(Model model) {
		Sale sale = new Sale();
		sale.setDiscountType("%");
		sale.setDiscount(0.0);
		model.addAttribute("sale", new Sale());
		model.addAttribute("message", "");
	    return "saleEntry";
	}
}
