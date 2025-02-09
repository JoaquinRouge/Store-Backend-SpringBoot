package com.joaquinrouge.bazar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.bazar.model.Product;
import com.joaquinrouge.bazar.repository.IProductRepository;

@Service
public class ProductService implements IProductService{
	
	public static final int LOW_STOCK = 5;

	@Autowired
	private IProductRepository repository;
	
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void createProduct(Product product) {
		// TODO Auto-generated method stub
		repository.save(product);
	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void editProduct(Product product) {
		// TODO Auto-generated method stub
		repository.save(product);
	}

	@Override
	public List<Product> getLowStockProducts() {
		List<Product> lowStockList = new ArrayList<>();
		
		for (Product product : repository.findAll()) {
			if(product.getStock() < ProductService.LOW_STOCK) {
				lowStockList.add(product);
			}
		}
		
		return lowStockList;
	}
	
}
