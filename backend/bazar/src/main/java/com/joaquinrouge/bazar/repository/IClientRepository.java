package com.joaquinrouge.bazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaquinrouge.bazar.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long>{

}
