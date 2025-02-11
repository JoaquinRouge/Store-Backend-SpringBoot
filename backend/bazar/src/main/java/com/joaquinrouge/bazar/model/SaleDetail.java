package com.joaquinrouge.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SaleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long SaleDetailId;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	
	
	private int requestedQuantity;
	
	public SaleDetail() {
		
	}

	public SaleDetail(Long saleDetailId, Product product, Sale sale, int requestedQuantity) {
		super();
		SaleDetailId = saleDetailId;
		this.product = product;
		this.sale = sale;
		this.requestedQuantity = requestedQuantity;
	}

	public Long getSaleDetailId() {
		return SaleDetailId;
	}

	public void setSaleDetailId(Long saleDetailId) {
		SaleDetailId = saleDetailId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public int getRequestedQuantity() {
		return requestedQuantity;
	}

	public void setRequestedQuantity(int requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}
	
	
	
}
