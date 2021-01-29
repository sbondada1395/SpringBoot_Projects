package com.johnlewis.pricereduction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johnlewis.pricereduction.model.PriceReductionDto;
import com.johnlewis.pricereduction.service.PriceReductionService;

@RestController
public class JLPriceReductionController {
	
	
	@Autowired
	private PriceReductionService priceReductionService;
	
	@GetMapping(value = "/products")
	public PriceReductionDto getPriceReducedProducts(@RequestParam("labelType") String priceLabelType,@RequestParam String dresses){
		if (!StringUtils.hasText(priceLabelType)) {
			priceLabelType = "ShowWasNow";
		}
		
		PriceReductionDto	products =priceReductionService.getProductsWithPriceReduction(priceLabelType);
		
	//	PriceReductionDto priceReduction = new PriceReductionDto();
	
		
		return products;
	}
	
	/*
	 * public static void main(String args[]) { JLPriceReductionController
	 * controller = new JLPriceReductionController();
	 * 
	 * controller.getPriceReducedProducts("", "d"); }
	 */
	

}
