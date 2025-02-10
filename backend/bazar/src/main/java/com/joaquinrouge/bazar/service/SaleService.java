package com.joaquinrouge.bazar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.bazar.dto.SaleByDateDTO;
import com.joaquinrouge.bazar.model.Client;
import com.joaquinrouge.bazar.model.Product;
import com.joaquinrouge.bazar.model.Sale;
import com.joaquinrouge.bazar.repository.ISaleRepository;

@Service
public class SaleService implements ISaleService{

	@Autowired
	private ISaleRepository repository;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IClientService clientService;
	
	@Override
	public List<Sale> getAllSale() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
    public void createSale(Sale sale) {
		//Actual products from the Sale in DB
        List<Product> productList = sale.getProductList(); 
        //Empty Product List
        List<Product> newProductList = new ArrayList<Product>();
        
        double totalPriceOfProductsInDB = 0;
        
        for (Product product : productList) {
        	//Actual product 
        	Product existingProduct = productService.getProduct(product.getProductId());
            
            if (existingProduct != null) {
            		//Discounts stock from product in DB
                    existingProduct.setStock(existingProduct.getStock() - 1);
                    //Adds the product with the stock discount to te new product list
                    newProductList.add(existingProduct);
                    //Increments the total price counter
                    totalPriceOfProductsInDB += existingProduct.getPrice();
            }
            
            
            //This cicle edits the original products to correct the stock
            for (Product updatedProduct : newProductList) {
            	productService.editProduct(updatedProduct);
			}
            
        }
        //Sets the total price
        sale.setTotal(totalPriceOfProductsInDB);
        //Sets the corrected product list
        sale.setProductList(newProductList);
        //Saves the sale in 
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
