package com.mytest.billapp.service.impl;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.mytest.billapp.service.LivePriceService;

@Service
public class LivePriceServiceImpl implements LivePriceService{

	final String uri = "http://www.svbcgold.com/LPriceSvbc.asmx/getSVBCPriceN8943";
    
	@Override
	public String getLivePriceObject() {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	    ResponseEntity<String> response = restTemplate.postForEntity( uri, request , String.class );
	    System.out.println(response);
		return response.getBody();
	}

}
