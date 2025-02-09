package com.joaquinrouge.bazar.service;

import java.util.List;

import com.joaquinrouge.bazar.model.Product;

public interface IProductService {
	public List<Product> getAllProduct();
	public void createProduct(Product client);
	public Product getProduct(Long id);
	public void deleteProduct(Long id);
	public void editProduct(Product client);
	public List<Product> getLowStockProducts();
}
