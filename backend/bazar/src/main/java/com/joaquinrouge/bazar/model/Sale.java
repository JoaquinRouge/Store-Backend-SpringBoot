package com.joaquinrouge.bazar.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long saleId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate saleDate;
	private double total;
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<SaleDetail> saleDetailList;
	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;
	
	public Sale() {
		
	}

	public Sale(Long saleId, LocalDate saleDate, double total, List<SaleDetail> productList, Client client) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.total = total;
		this.saleDetailList = productList;
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

	public List<SaleDetail> getSaleDetailList() {
		return saleDetailList;
	}

	public void setSaleDetailList(List<SaleDetail> productList) {
		this.saleDetailList = productList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	
	
}
