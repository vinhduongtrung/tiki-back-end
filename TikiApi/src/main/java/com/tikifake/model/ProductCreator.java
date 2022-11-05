package com.tikifake.model;

import java.time.LocalDateTime;

import com.tikifake.entity.CategorySub;
import com.tikifake.entity.Product;

public class ProductCreator {

	private String name;

	private String description;

	private double price;

	private String image;

	private String brand;

	private String madeIn;

	private Long categorySubId;

	public Product convertDTOToEntity(CategorySub categorySub) {
		Product product = new Product();
		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(this.price);
		product.setBrand(this.brand);
		product.setImage(this.image);
		
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		product.setCreatedDate(LocalDateTime.of(year, month, day, hour, minute,second));
		
		product.setMadeIn(this.madeIn);
		product.setCategorySub(categorySub);
		return product;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public String getImage() {
		return image;
	}

	public String getBrand() {
		return brand;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public Long getCategorySubId() {
		return categorySubId;
	}

}
