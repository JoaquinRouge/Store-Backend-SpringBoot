package com.joaquinrouge.bazar.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.joaquinrouge.bazar.dto.SaleByDateDTO;
import com.joaquinrouge.bazar.model.Product;
import com.joaquinrouge.bazar.model.Sale;
import com.joaquinrouge.bazar.service.ISaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
	
	@Autowired
	private ISaleService service;
	
	@GetMapping("/all")
	public List<Sale> getSales(){
		return service.getAllSale();
	}
	
	@PostMapping("/save")
	public void createSale(@RequestBody Sale Sale) {
		service.createSale(Sale);
	}
	
	@GetMapping("/get/{id}")
	public Sale getSale(@PathVariable Long id) {
		return service.getSale(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteSale(@PathVariable Long id) {
		service.deleteSale(id);
	}
	
	@PutMapping("/edit")
	public void editSale(Sale Sale) {
		service.editSale(Sale);
	}
	
	@GetMapping("/products/{saleId}")
	public List<Product> getProductsFromSale(@PathVariable Long saleId){
		return service.getProductsFromSale(saleId);
	}
	
	@GetMapping("/date/{date}")
	public SaleByDateDTO getSalesByDate(@PathVariable String date) {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate saleDate = LocalDate.parse(date, formatter);
		
		return service.getSalesByDate(saleDate);
	}

}
