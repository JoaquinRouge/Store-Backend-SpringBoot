package com.joaquinrouge.bazar.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long saleId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate saleDate;
	private double total;
	@ManyToMany
	@JoinTable(
	    name = "sale_product",
	    joinColumns = @JoinColumn(name = "sale_id"),
	    inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Product> productList;
	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;
	
	public Sale() {
		
	}

	public Sale(Long saleId, LocalDate saleDate, double total, List<Product> productList, Client client) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.total = total;
		this.productList = productList;
		this.client = client;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	
	
}
