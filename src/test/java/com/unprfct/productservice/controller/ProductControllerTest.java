package com.unprfct.productservice.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unprfct.productservice.dao.ProductRepository;
import com.unprfct.productservice.model.Category;
import com.unprfct.productservice.model.Product;
import com.unprfct.productservice.model.SubCategory;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository productRepository;
	
	//Mock product for testing purpose
	private Product mockProduct() {
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
		
		return pro;
	}
	
	
	
	@Test
	@DisplayName("Get all products")
	void getProductsTest() throws Exception {
		Product pro = mockProduct();
		
		ArrayList<Product> plist = new ArrayList<>();
		plist.add(pro);
		
		when(productRepository.findAll()).thenReturn(plist);
		
		mockMvc.perform(get("/getProducts")).andExpect(status().isOk())
		.andExpect(content().json("[{\"productId\":1,\"category\":{\"categoryId\":1,\"categoryName\":\"Clothes\",\"description\":null},\"productName\":\"Red Tee\",\"details\":\"Good\",\"description\":\"Good T-Shirt\",\"unitPrice\":1200.0,\"size\":null,\"color\":\"Red\",\"discount\":35.0,\"brand\":\"Levis\",\"sub\":{\"subId\":4,\"subName\":\"T-Shrit\",\"subDetails\":null},\"quantity\":10,\"availability\":true,\"tags\":null,\"imageurl\":\"xyz\"}]"));
	}
	
	@Test
	@DisplayName("Get Product by ID")
	void getProductByIdTest() throws Exception {
		Product pro = mockProduct();
		
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(pro));
		
		mockMvc.perform(get("/get/1")).andExpect(status().isOk())
		.andExpect(content().json("{\"productId\":1,\"category\":{\"categoryId\":1,\"categoryName\":\"Clothes\",\"description\":null},\"productName\":\"Red Tee\",\"details\":\"Good\",\"description\":\"Good T-Shirt\",\"unitPrice\":1200.0,\"size\":null,\"color\":\"Red\",\"discount\":35.0,\"brand\":\"Levis\",\"sub\":{\"subId\":4,\"subName\":\"T-Shrit\",\"subDetails\":null},\"quantity\":10,\"availability\":true,\"tags\":null,\"imageurl\":\"xyz\"}"));
	}
	
	@Test
	@DisplayName("Add Product")
	void addProductTest()throws Exception {
		Product pro = mockProduct();
		
		//when(productRepository.save(any(Product.class))).thenReturn(pro);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(pro);
		
		//mockMvc.perform(post("/addProduct").content("{\"productId\":1,\"category\":{\"categoryId\":1,\"categoryName\":\"Clothes\",\"description\":null},\"productName\":\"Red Tee\",\"details\":\"Good\",\"description\":\"Good T-Shirt\",\"unitPrice\":1200.0,\"size\":null,\"color\":\"Red\",\"discount\":35.0,\"brand\":\"Levis\",\"sub\":{\"subId\":4,\"subName\":\"T-Shrit\",\"subDetails\":null},\"quantity\":10,\"availability\":true,\"tags\":null,\"imageurl\":\"xyz\"}").
		mockMvc.perform(post("/addProduct").content(json).
		contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string("product added"));
	}
	
	@Test
	@DisplayName("Delete Product")
	void deleteProductTest() throws Exception {
		Product pro = mockProduct();
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(pro));
		mockMvc.perform(delete("/deleteProduct/1")).andExpect(status().isOk())
		.andExpect(content().string("Product Deleted"));
	}
	
	@Test
	@DisplayName("Update Product")
	void updateProductTest() throws Exception {
		Product product = mockProduct();
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
		
		Product p = new Product();
		p.setProductId(Long.valueOf(1));
		p.setDiscount(product.getDiscount());
		p.setAvailability(product.isAvailability());
		p.setColor(product.getColor());
		p.setQuantity(product.getQuantity());
		p.setSize(product.getSize());
		p.setUnitPrice(product.getUnitPrice());
		
		when(productRepository.save(any(Product.class))).thenReturn(p);
		
		//ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		//String json = ow.writeValueAsString(p);
		
		
		mockMvc.perform(put("/updateProduct/1").content("{\"productId\":1,\"category\":{\"categoryId\":1,\"categoryName\":\"Clothes\",\"description\":null},\"productName\":\"Red Tee\",\"details\":\"Good\",\"description\":\"Good T-Shirt\",\"unitPrice\":1200.0,\"size\":null,\"color\":\"Red\",\"discount\":35.0,\"brand\":\"Levis\",\"sub\":{\"subId\":4,\"subName\":\"T-Shrit\",\"subDetails\":null},\"quantity\":10,\"availability\":true,\"tags\":null,\"imageurl\":\"xyz\"}").
				contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json("{\"productId\":1,\"category\":null,\"productName\":null,\"details\":null,\"description\":null,\"unitPrice\":1200.0,\"size\":null,\"color\":\"Red\",\"discount\":35.0,\"brand\":null,\"sub\":null,\"quantity\":10,\"availability\":true,\"tags\":null,\"imageurl\":null}"));
	}
}
