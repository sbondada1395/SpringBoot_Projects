package com.johnlewis.pricereduction.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PriceReductionDto {
	
	
	public List<PriceReductionProductDetails> products;

	public List<PriceReductionProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<PriceReductionProductDetails> products) {
		this.products = products;
	}

}
