package com.fdmgroup.GameBlog.util;

public class Filters {
	
	private String color;
	private String category;
	private String type;
	private String minPrice;
	private String maxPrice;
	
	public Filters() {
		this.color = "No filter";
		this.category = "No filter";
		this.minPrice = "";
		this.maxPrice = "";
		this.type = "No filter";
	}
	
	public Filters(String color, String category, String minPrice, String maxPrice, String type) {
		super();
		this.color = color;
		this.category = category;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Filters [color=" + color + ", category=" + category + ", type=" + type + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + "]";
	}
	
	
	
	

}
