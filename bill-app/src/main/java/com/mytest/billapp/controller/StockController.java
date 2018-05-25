package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Stock;
import com.mytest.billapp.service.StockService;

@Controller
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@RequestMapping(value = "stockList", method = RequestMethod.POST)
	public String getAllStock(Model model) {
		model.addAttribute("stockList", stockService.findAll());
		model.addAttribute("stock", new Stock());
		model.addAttribute("selectedId","");
	    return "stock";
	}
	
	@RequestMapping(value = "saveStock", method = RequestMethod.POST)
	public String saveStock(@ModelAttribute Stock stock, Model model) {
		try {
			stockService.save(stock);
			model.addAttribute("stock", new Stock());
			model.addAttribute("stockList", stockService.findAll());
			model.addAttribute("message", "Succesfully Saved.");
			model.addAttribute("selectedId","");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "stock";
		}
	    return "stock";
	}
	
	
	@RequestMapping(value = "stock", method = RequestMethod.POST)
	public String addStock(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("stock", new Stock());
		try {
			model.addAttribute("stock", stockService.findById(selectedId));
			model.addAttribute("stockList", stockService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "stock";
		}
		return "stock";
	}
	
	
	@RequestMapping(value = "deleteStock", method = RequestMethod.POST)
	public String deleteStock(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			Stock stock = stockService.findById(selectedId);
			     //   .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			stockService.delete(stock);
			model.addAttribute("stockList", stockService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllStock(model);
		}
	    return addStock(0l, model);
	}
}
