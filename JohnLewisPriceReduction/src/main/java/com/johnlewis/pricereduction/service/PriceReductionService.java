package com.johnlewis.pricereduction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.johnlewis.pricereduction.model.PriceReductionDto;
import com.johnlewis.productinfo.model.JohnLewisProducts;

@Service
public class PriceReductionService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PriceReductionResponseBuilder responseBuilder;

	public PriceReductionDto getProductsWithPriceReduction(String priceLabelType){
		JohnLewisProducts jlProducts= getProductDetails();
		System.out.println("Products Received : "+jlProducts.getProducts());
		
		PriceReductionDto responseProducts =	responseBuilder.responseMapper(jlProducts, priceLabelType);
		
		
		return responseProducts;
	}
	
	private JohnLewisProducts getProductDetails() {
		
		String url = "https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
		        // Add query parameter
		        .queryParam("q", "dresses")
		        .queryParam("key", "AIzaSyDD_6O5gUgC4tRW5f9kxC0_76XRC8W7_mI");
		System.out.println(builder.build().toUriString());
		ResponseEntity<JohnLewisProducts> products = restTemplate.exchange(builder.build().toUri(),HttpMethod.GET,null,JohnLewisProducts.class);
		
		return products.getBody();
	}
}
