package com.unprfct.productservice.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="subCategory")
public class SubCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long subId;

	private String subName;
	private String subDetails;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sub")
	private List<Product> product;

	public SubCategory() {
		super();
	}


	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubDetails() {
		return subDetails;
	}

	public void setSubDetails(String subDetails) {
		this.subDetails = subDetails;
	}

	
	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	

}
