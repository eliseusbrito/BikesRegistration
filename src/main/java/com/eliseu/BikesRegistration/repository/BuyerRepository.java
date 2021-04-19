package com.eliseu.BikesRegistration.repository;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    List<Buyer> findByNome(String nome);

}
