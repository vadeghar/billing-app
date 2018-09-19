package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.billapp.service.LivePriceService;

@RestController
@RequestMapping("/public")
public class PublicAjaxController {
	
	@Autowired
	LivePriceService livePriceService;
	
	@GetMapping("liveprice")
	public String getLivePrice() {
		return livePriceService.getLivePriceObject();
	}

}
