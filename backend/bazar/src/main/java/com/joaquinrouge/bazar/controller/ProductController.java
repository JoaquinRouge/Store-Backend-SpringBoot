package com.joaquinrouge.bazar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquinrouge.bazar.model.Product;
import com.joaquinrouge.bazar.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private IProductService service;
	
	@GetMapping("/all")
	public List<Product> getProducts(){
		return service.getAllProduct();
	}
	
	@PostMapping("/save")
	public void createProduct(@RequestBody Product product) {
		service.createProduct(product);
	}
	
	@GetMapping("/get/{id}")
	public Product getProduct(@PathVariable Long id) {
		return service.getProduct(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable Long id) {
		service.deleteProduct(id);
	}
	
	@PutMapping("/edit/{id}")
	public void editProduct(@PathVariable Long id,String name,String brand,double price, int stock) {
		service.editProduct(id,name,brand,price,stock);
	}
	
	@GetMapping("/lowstock")
	public List<Product> getLowStockProducts(){
		return service.getLowStockProducts();
	}
	
}
