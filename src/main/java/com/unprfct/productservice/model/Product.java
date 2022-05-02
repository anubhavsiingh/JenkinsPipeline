package com.unprfct.productservice.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="product")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "categoryid", nullable = false)
	private Category category;
	
	private String productName;
	private String details;
	private String description;
	private float unitPrice;
	private String size;
	private String color;
	private float discount;
	private String brand;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="subcategoryId", nullable = false)
	private SubCategory sub;
	
	private int quantity;
	private boolean availability;
	private String tags;
	private String imageurl;
	
//	@JsonIgnore
//	@OneToMany(cascade =CascadeType.ALL, mappedBy="product" )
//	private List<ProductReview> review;

	
	public Product() {
		super();
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
//	public List<ProductReview> getReview() {
//		return review;
//	}
//
//	public void setReview(List<ProductReview> review) {
//		this.review = review;
//	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public SubCategory getSub() {
		return sub;
	}

	public void setSub(SubCategory sub) {
		this.sub = sub;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}

