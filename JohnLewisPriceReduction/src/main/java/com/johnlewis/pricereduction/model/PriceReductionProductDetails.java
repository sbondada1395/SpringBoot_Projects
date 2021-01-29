package com.johnlewis.pricereduction.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceReductionProductDetails {

	public String productId;
	
	public String title;
	public List<PriceReductionColorSwatches> colorSwatches;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<PriceReductionColorSwatches> getColorSwatches() {
		return colorSwatches;
	}
	public void setColorSwatches(List<PriceReductionColorSwatches> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}
//	public String getPriceLabel() {
//		return priceLabel;
//	}
//	public void setPriceLabel(String priceLabel) {
//		this.priceLabel = priceLabel;
//	}
	public String now;
	public String was;
	public String getWas() {
		return was;
	}
	public void setWas(String was) {
		this.was = was;
	}
	public String then;
	public String getNow() {
		return now;
	}
	public void setNow(String now) {
		this.now = now;
	}
	public String getThen() {
		return then;
	}
	public void setThen(String then) {
		this.then = then;
	}
	public double getReduction() {
		return reduction;
	}
	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	@JsonIgnore
	public double reduction;
	public String discount;
	
	
//	public String priceLabel;
}
