package com.joaquinrouge.bazar.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.joaquinrouge.bazar.dto.SaleByDateDTO;
import com.joaquinrouge.bazar.model.Product;
import com.joaquinrouge.bazar.model.Sale;

public interface ISaleService {
	public List<Sale> getAllSale();
	public ResponseEntity<?> createSale(Sale client);
	public Sale getSale(Long id);
	public void deleteSale(Long id);
	public void editSale(Sale client);
	public List<Product> getProductsFromSale(Long saleId);
	public SaleByDateDTO getSalesByDate(LocalDate saleDate);
}
