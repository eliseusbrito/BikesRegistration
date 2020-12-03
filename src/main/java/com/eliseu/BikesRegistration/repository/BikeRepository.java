package com.eliseu.BikesRegistration.repository;

import com.eliseu.BikesRegistration.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {

    List<Bike> findByDescription(String description);

}
