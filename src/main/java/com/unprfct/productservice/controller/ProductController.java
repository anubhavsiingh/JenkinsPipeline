package com.unprfct.productservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unprfct.productservice.dao.CategoryRepository;
import com.unprfct.productservice.dao.ProductRepository;
import com.unprfct.productservice.dao.SubCategoryRepository;
import com.unprfct.productservice.model.Category;
import com.unprfct.productservice.model.Product;
import com.unprfct.productservice.model.SubCategory;
import com.unprfct.productservice.service.ShoppingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private ProductRepository pRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private SubCategoryRepository subRepo;

	@PostMapping("/addProduct")
	public String add(@RequestBody Product product) {

		Category c = product.getCategory();
		product.setCategory(c);

		SubCategory s = product.getSub();

		product.setSub(s);
		List<Product> d = new ArrayList<Product>();
		d.add(product);

		categoryRepo.save(c);
		subRepo.save(s);
		c.setProduct(d);
		s.setProduct(d);
		pRepo.save(product);

		shoppingService.saveProduct(product);
		return "product added";
	}
	
	@GetMapping("/getProducts")
	public List<Product> getAllProducts() {
		return shoppingService.getAllProducts();
	}

	@GetMapping(value = "/get/{id}")
	public Product getProduct(@PathVariable("id") Long pId) throws NoSuchElementException {
		Product p = shoppingService.getProduct(pId).get();
		return p;
	}

	@PutMapping("updateProduct/{id}")
	public Product updateProduct(@PathVariable(value = "id") Long pId, @Validated @RequestBody Product product)
			throws ResourceNotFoundException {

		Product p = pRepo.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product is not available"));
		p.setDiscount(product.getDiscount());
		p.setAvailability(product.isAvailability());
		p.setColor(product.getColor());
		p.setQuantity(product.getQuantity());
		p.setSize(product.getSize());
		p.setUnitPrice(product.getUnitPrice());

		return pRepo.save(p);

	}

	@DeleteMapping("deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value = "id") Long pId) throws ResourceNotFoundException {

		Product product = pRepo.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		pRepo.delete(product);

		return "Product Deleted";
	}
}
