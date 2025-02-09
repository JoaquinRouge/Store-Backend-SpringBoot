package com.joaquinrouge.bazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaquinrouge.bazar.model.Sale;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long>{

}
