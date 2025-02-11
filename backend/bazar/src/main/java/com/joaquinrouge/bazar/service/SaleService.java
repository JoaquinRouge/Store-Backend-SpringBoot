package com.joaquinrouge.bazar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joaquinrouge.bazar.dto.SaleByDateDTO;
import com.joaquinrouge.bazar.model.Client;
import com.joaquinrouge.bazar.model.Product;
import com.joaquinrouge.bazar.model.Sale;
import com.joaquinrouge.bazar.model.SaleDetail;
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
    public ResponseEntity<?> createSale(Sale sale) {
		//Actual products from the Sale in DB
        List<SaleDetail> SaleDetailList = sale.getSaleDetailList(); 
        //Empty Product List
        List<SaleDetail> newSaleDetailList = new ArrayList<SaleDetail>();
        
        double totalPriceOfProductsInDB = 0;
        
        for (SaleDetail saleDetail : SaleDetailList) {
        	//Actual product 
        	Product existingProduct = productService.getProduct(saleDetail.getProduct().getProductId());
            
            if (existingProduct != null) {
            	
            		if(existingProduct.getStock() >= saleDetail.getRequestedQuantity()) {
            			//Discounts stock from product in DB
            			existingProduct.setStock(existingProduct.getStock() - saleDetail.getRequestedQuantity());
            			//Adds the product with the stock discount to the new product list
            			newSaleDetailList.add(new SaleDetail(saleDetail.getSaleDetailId(),
            					existingProduct,sale,saleDetail.getRequestedQuantity()));
            			//Increments the total price counter
            			totalPriceOfProductsInDB += existingProduct.getPrice() * saleDetail.getRequestedQuantity();
            			
            		}else {
            			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock for product with id: "
            					+ existingProduct.getProductId());
            		}
            }else {
            	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found for id: "
            			+ saleDetail.getProduct().getProductId());
            }
            
        }
        
        //This cicle edits the original products to correct the stock
        for (SaleDetail updatedSaleDetail : newSaleDetailList) {
        	productService.editProduct(updatedSaleDetail.getProduct());
        }
        
        //Sets the total price
        sale.setTotal(totalPriceOfProductsInDB);
        //Sets the corrected product list
        sale.setSaleDetailList(newSaleDetailList);
        //Saves the sale in DB
        repository.save(sale);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sale);
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
		
		List<SaleDetail> saleDetailList = this.getSale(saleId).getSaleDetailList();
		List<Product> products = new ArrayList<>();
		
		for (SaleDetail saleDetail : saleDetailList) {
			products.add(productService.getProduct(saleDetail.getProduct().getProductId()));
		}
		
		return products;
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
