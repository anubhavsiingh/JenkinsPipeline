package com.unprfct.productservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.unprfct.productservice.dao.CategoryRepository;
import com.unprfct.productservice.dao.ProductRepository;
import com.unprfct.productservice.dao.SubCategoryRepository;
import com.unprfct.productservice.model.Category;
import com.unprfct.productservice.model.Product;
import com.unprfct.productservice.model.SubCategory;

class ShoppingServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private SubCategoryRepository subCategoryRepo;
	
	@InjectMocks
	private ShoppingServiceImpl serviceImpl;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@DisplayName("Fetching Data")
	void getAllProductsTest() {
		when(productRepository.findAll()).thenReturn(List.of(new Product(), new Product()));
        List<Product> products = serviceImpl.getAllProducts();

        assertThat(products).isNotNull();
        assertThat(2).isEqualTo(products.size());
	}
	
	@Test
    @DisplayName("No products found in the system")
    void listAllProductsInCaseOfEmptyResult() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
//        assertThrows(ResourceNotFoundException.class, () -> serviceImpl.getAllProducts());
        assertThat(new ArrayList<>()).isEqualTo(serviceImpl.getAllProducts());
	}
	
	@Test
    @DisplayName("Saving product")
    void saveProductTest() {
		Product pro = new Product();
		pro.setProductId(Long.valueOf(1));
		pro.setBrand("Levis");
		pro.setAvailability(true);
		pro.setColor("Red");
		pro.setDescription("Good T-Shirt");
		pro.setImageurl("xyz");
		pro.setDiscount(35);
		pro.setDetails("Good");
		pro.setProductName("Red Tee");
		pro.setUnitPrice(1200);
		pro.setQuantity(10);
		
		Category c = new Category();
		c.setCategoryId(Long.valueOf(1));
		c.setCategoryName("Clothes");
		pro.setCategory(c);
		
		SubCategory sc = new SubCategory();
		sc.setSubId(Long.valueOf(4));
		sc.setSubName("T-Shrit");
		pro.setSub(sc);
		
		
        when(productRepository.save(any(Product.class))).thenReturn(pro);
        assertThat(serviceImpl.saveProduct(pro)).isEqualTo(pro);
	}
	

	@Test
    @DisplayName("Delete product")
    void deleteProductTest() {
		Product pro = new Product();
		pro.setProductId(Long.valueOf(1));
		pro.setBrand("Levis");
		pro.setAvailability(true);
		pro.setColor("Red");
		pro.setDescription("Good T-Shirt");
		pro.setImageurl("xyz");
		pro.setDiscount(35);
		pro.setDetails("Good");
		pro.setProductName("Red Tee");
		pro.setUnitPrice(1200);
		pro.setQuantity(10);
		
		Category c = new Category();
		c.setCategoryId(Long.valueOf(1));
		c.setCategoryName("Clothes");
		pro.setCategory(c);
		
		SubCategory sc = new SubCategory();
		sc.setSubId(Long.valueOf(4));
		sc.setSubName("T-Shrit");
		pro.setSub(sc);
		
		
//        when(productRepository.deleteById(Long.valueOf(1)).thenReturn("Product deleted.");
        assertThat(serviceImpl.deleteProduct(Long.valueOf(1))).isEqualTo("Product Deleted");
	}
	
}
