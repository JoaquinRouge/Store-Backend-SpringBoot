package com.joaquinrouge.bazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaquinrouge.bazar.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{

}
