package com.johnlewis.productinfo.model;

import java.util.List;

public class JLProductInfo implements JLProductIFace{
	
	private String productId;
	private String title;
	private List<JLColorSwatches> colorSwatches;
	private JLPrice price;
	
	//getters and setters
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

	public List<JLColorSwatches> getColorSwatches() {
		return colorSwatches;
	}

	public void setColorSwatches(List<JLColorSwatches> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}

	public JLPrice getPrice() {
		return price;
	}

	public void setPrice(JLPrice price) {
		this.price = price;
	}

	@Override
	public void getName() {
		// TODO Auto-generated method stub
		
	}

}
