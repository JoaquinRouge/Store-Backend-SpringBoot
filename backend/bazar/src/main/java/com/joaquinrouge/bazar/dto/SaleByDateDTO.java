package com.joaquinrouge.bazar.dto;

public class SaleByDateDTO {
	private int saleCount;
	private double totalSpent;
	
	public SaleByDateDTO() {
		
	}

	public SaleByDateDTO(int saleCount, double totalSpent) {
		super();
		this.saleCount = saleCount;
		this.totalSpent = totalSpent;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public double getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(double totalSpent) {
		this.totalSpent = totalSpent;
	}
	
	
}
