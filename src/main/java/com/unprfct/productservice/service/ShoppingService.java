package com.unprfct.productservice.service;

import java.util.List;
import java.util.Optional;

import com.unprfct.productservice.model.Category;
import com.unprfct.productservice.model.Product;
import com.unprfct.productservice.model.SubCategory;

public interface ShoppingService {
	public List<Product> getAllProducts();
	public Product saveProduct(Product product);
	public Optional<Product> getProduct(Long id);
	public Product findByProductName(String productName);
	public Product findByUnitPrice(float unitPrice);
	public String deleteProduct(Long id);
	
	public List<Category> findAll();
	public SubCategory saveSub(SubCategory subcategory);
	
//	public List<ProductReview> getReview();
//	public ProductReview addReview(ProductReview review);
	
	
}
