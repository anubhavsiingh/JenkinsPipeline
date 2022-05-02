package com.unprfct.productservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.unprfct.productservice.dao.*;
import com.unprfct.productservice.model.Category;
import com.unprfct.productservice.model.Product;
import com.unprfct.productservice.model.SubCategory;


@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService  {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepo;
	
//	@Autowired
//	private CustomerRepositiory customerRepo;
//	
//	@Autowired
//	private ProductReviewRepository reviewRepo;
	
		
	
	@Override
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}
	
	@Override
	public Product findByProductName(String productName) {
		
		return productRepository.findByProductName(productName);
	}

	@Override
	public Product findByUnitPrice(float unitPrice) {
		
		return productRepository.findByUnitPrice(unitPrice);
	}
	
	@Override
	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}


	@Override
	public String deleteProduct(Long id) {
		
		productRepository.deleteById(id);
		return "Product Deleted";
	}

	@Override
	public List<Category> findAll() {
		
		return categoryRepository.findAll();
	}

	@Override
	public SubCategory saveSub(SubCategory subcategory) {
		
		return subCategoryRepo.save(subcategory);
	}

//	@Override
//	public List<ProductReview> getReview() {
//		
//		return reviewRepo.findAll();
//	}
//
//	@Override
//	public ProductReview addReview(ProductReview review) {
//		
//		return reviewRepo.save(review);
//	}

	
	

}
