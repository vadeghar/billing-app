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
import com.mytest.billapp.model.Stock;
import com.mytest.billapp.service.StockService;

@Controller
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@GetMapping("/stockList")
	public String getAllStock(Model model) {
		model.addAttribute("stockList", stockService.findAll());
	    return "stockList";
	}
	
	//@PostMapping("/saveStock")
	@RequestMapping(value = "saveStock", method = RequestMethod.POST)
	public String saveStock(@ModelAttribute Stock stock, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("stock", stockService.save(stock));
	    return "stock";
	}
	
	@GetMapping("/stock/{id}")
	public String getStockById(@PathVariable(value = "id") Long id, Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("stock", new Stock());
		model.addAttribute("stock", stockService.findById(id));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "stock";
	}
	
	@PutMapping("/stock/update/{id}")
	public String updateStock(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute Stock stock, Model model) {

	    Stock stock1 = stockService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    //stock1.setTitle(stock.getTitle());
	    //stock1.setContent(stock.getContent());
	    model.addAttribute("stock",stockService.save(stock1));
	    return "stock";
	}
	
	@GetMapping("/stock/delete/{id}")
	public String deleteStock(@PathVariable(value = "id") Long id, Model model) {
	    Stock stock = stockService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    stockService.delete(stock);
	    return getAllStock(model);
	}
}
