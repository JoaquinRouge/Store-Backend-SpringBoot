package com.joaquinrouge.bazar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.bazar.dto.SaleByDateDTO;
import com.joaquinrouge.bazar.model.Product;
import com.joaquinrouge.bazar.model.Sale;
import com.joaquinrouge.bazar.repository.IProductRepository;
import com.joaquinrouge.bazar.repository.ISaleRepository;

@Service
public class SaleService implements ISaleService{

	@Autowired
	private ISaleRepository repository;
	
	@Autowired
	private IProductService productService;
	
	@Override
	public List<Sale> getAllSale() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
    public void createSale(Sale sale) {

        List<Product> productList = sale.getProductList(); //Actual products from the Sale in DB
        List<Product> newProductList = new ArrayList<Product>(); //Empty Product List

        for (Product product : productList) {
             
        	Product existingProduct = productService.getProduct(product.getProductId());
            
            if (existingProduct != null) {
                    existingProduct.setStock(existingProduct.getStock() - 1);
                    newProductList.add(existingProduct);
            }
            
            for (Product updatedProduct : newProductList) {
            	productService.editProduct(updatedProduct);
			}
            
        }
        sale.setProductList(newProductList);
        repository.save(sale);
    }

	@Override
	public Sale getSale(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public void deleteSale(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void editSale(Sale sale) {
		// TODO Auto-generated method stub
		repository.save(sale);
	}

	@Override
	public List<Product> getProductsFromSale(Long saleId) {
		// TODO Auto-generated method stub
		return this.getSale(saleId).getProductList();
	}

	@Override
	public SaleByDateDTO getSalesByDate(LocalDate date) {
		
		int saleCount = 0;
		double totalSpent = 0;
		
		for (Sale sale : this.getAllSale()) {
			if(sale.getSaleDate().isEqual(date)) {
				saleCount++;
				totalSpent += sale.getTotal();
			}
		}
		return new SaleByDateDTO(saleCount,totalSpent);
	}

}
