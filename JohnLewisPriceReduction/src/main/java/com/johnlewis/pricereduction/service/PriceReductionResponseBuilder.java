package com.johnlewis.pricereduction.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.johnlewis.pricereduction.model.PriceReductionColorSwatches;
import com.johnlewis.pricereduction.model.PriceReductionDto;
import com.johnlewis.pricereduction.model.PriceReductionProductDetails;
import com.johnlewis.productinfo.model.JLPrice;
import com.johnlewis.productinfo.model.JLProductInfo;
import com.johnlewis.productinfo.model.JohnLewisProducts;

@Component
public class PriceReductionResponseBuilder {
	
	static Map<String, String> colorMap = new HashMap<>();
	static Map<String, String> currencyMap = new HashMap<>();
	static {
		colorMap.put("Black", 	"#000000");
		colorMap.put("White", 	"#FFFFFF");
		colorMap.put("Lime", 	"#00FF00");
		colorMap.put("Blue", 	"#0000FF");
		colorMap.put("Yellow", 	"#FFFF00");
		colorMap.put("Cyan", 	"#00FFFF");
		colorMap.put("Magenta", "#FF00FF");
		colorMap.put("Silver",	"#C0C0C0");
		colorMap.put("Gray",	"#808080");
		colorMap.put("Maroon",	"#800000");
		colorMap.put("Olive",	"#808000");
		colorMap.put("Green",	"#008000");
		colorMap.put("Purple",	"#800080");
		colorMap.put("Teal",	"#008080");
		colorMap.put("Navy",	"#000080");
		colorMap.put("Orange",	"#000080");
		colorMap.put("Pink",	"#000080");
		colorMap.put("Natural",	"#000080");
		
		currencyMap.put("GBP", "£");
		currencyMap.put("USD", "$");
		
	}

	/**
	 * Converts the response from JohnLewis product info to Price Reduction value
	 * object
	 * 
	 * @param jlProducts
	 * @return
	 */
	public PriceReductionDto responseMapper(JohnLewisProducts jlProducts, String priceLabelType) {
		PriceReductionDto dto=new PriceReductionDto();
		ArrayList<JLProductInfo> jlproducts = jlProducts.getProducts();
		  List<PriceReductionProductDetails> prprod=jlproducts.stream()
				  .filter(p -> NumberUtils.isParsable(p.getPrice().getNow().toString()))
				  .filter(p -> NumberUtils.isParsable(p.getPrice().getWas()))
				  .map( x->{
		  PriceReductionProductDetails prProduct = new PriceReductionProductDetails();
		  prProduct.setProductId(x.getProductId());
		  prProduct.setTitle(x.getTitle());
		  if (Objects.nonNull(x.getColorSwatches())) {
			  List<PriceReductionColorSwatches> colorSwatchesList = x.getColorSwatches().stream().map(cswatch -> {
				 PriceReductionColorSwatches colorSwatch = new PriceReductionColorSwatches();
				 colorSwatch.setColor(cswatch.getBasicColor());
				 colorSwatch.setRgbColor(colorMap.get(cswatch.getBasicColor()));
				 colorSwatch.setSkuid(cswatch.getSkuId());
				 return colorSwatch;
			  }).collect(Collectors.toList());
			  prProduct.setColorSwatches(colorSwatchesList);
			  
		  }
		  
		  if (Objects.nonNull(x.getPrice())) {
			  populateNowWasPrice(x.getPrice(), prProduct, priceLabelType);
			  populateThenPrice(x.getPrice(), prProduct, priceLabelType);
			  populateDiscount(x.getPrice(), prProduct, priceLabelType);
			  populateReductiont(x.getPrice(), prProduct);
			  
		  }
//		  prProduct.setPriceLabel(priceLabelType);
		  return prProduct;
		  }).filter(rprod -> rprod.getReduction() > 0)
				  .sorted(Comparator.comparingDouble(PriceReductionProductDetails::getReduction).reversed())
				  .collect(Collectors.toList());
		  
		dto.setProducts(prprod);
		return dto;

	}
	
	
	private String formatPrice(String price, String currency) {
		return currencyMap.get(currency)+price;
	}
	
	private void populateNowWasPrice(JLPrice price, PriceReductionProductDetails prProduct, String priceLabelType) {
		if(!"ShowPercDiscount".equals(priceLabelType)) {
		String now = price.getNow().toString();
		prProduct.setNow(formatPrice(determineDisplayPrice(now), price.getCurrency()));
		
		String was = price.getWas();
		prProduct.setWas(formatPrice(determineDisplayPrice(was), price.getCurrency()));
		}
	}
	
	
	private void populateThenPrice(JLPrice price, PriceReductionProductDetails prProduct, String priceLabelType) {
		if ("ShowWasThenNow".equals(priceLabelType)) {
			if(StringUtils.hasText(price.getThen2())) {
				prProduct.setThen(formatPrice(determineDisplayPrice(price.getThen2()), price.getCurrency()));
				
			} else if(StringUtils.hasText(price.getThen1().toString())) {
				prProduct.setThen(formatPrice(determineDisplayPrice(price.getThen1().toString()), price.getCurrency()));
			}
		}
	}
	
	private void populateDiscount(JLPrice price, PriceReductionProductDetails prProduct, String priceLabelType) {
		if ("ShowPercDiscount".equals(priceLabelType)) {
			String now = price.getNow().toString();
			String was = price.getWas();
			if (StringUtils.hasText(now) && StringUtils.hasText(was)) {
				int wasPrice = (int)(Math.round(Double.parseDouble(was)));
				int nowPrice = (int)(Math.round(Double.parseDouble(now)));
				BigDecimal ws = new BigDecimal(wasPrice);
				BigDecimal ns = new BigDecimal(nowPrice);
				BigDecimal discount = ws.subtract(ns);
				double discountPercent = discount.divide(ws,2,RoundingMode.CEILING).multiply(new BigDecimal(100)).doubleValue();
					prProduct.setDiscount(discountPercent + "% of"+ formatPrice(determineDisplayPrice(was), price.getCurrency()));
				}
			}
	}
		
		
	private void populateReductiont(JLPrice price, PriceReductionProductDetails prProduct) {
		String now = price.getNow().toString();
		String was = price.getWas();
		if (StringUtils.hasText(now) && StringUtils.hasText(was)) {
			int wasPrice = (int) (Math.round(Double.parseDouble(was)));
			int nowPrice = (int) (Math.round(Double.parseDouble(now)));
			if (nowPrice < wasPrice) {
				BigDecimal ws = new BigDecimal(wasPrice);
				BigDecimal ns = new BigDecimal(nowPrice);
				BigDecimal reduction = ws.subtract(ns);
				double reductionPercent = reduction.divide(ws,2,RoundingMode.CEILING).multiply(new BigDecimal(100)).doubleValue();
				prProduct.setReduction(reductionPercent);
			} else {
				prProduct.setReduction(0.00);
			}
		}
	}
	
	
	private String determineDisplayPrice(String price) {
		if (StringUtils.hasText(price)) {
			double priceDouble = Double.parseDouble(price);
			if(priceDouble < 10) {
				return Double.toString(priceDouble);
			} else {
				return (int)(Math.round(priceDouble))+"";
			}
		}
		return "";
	}

}
