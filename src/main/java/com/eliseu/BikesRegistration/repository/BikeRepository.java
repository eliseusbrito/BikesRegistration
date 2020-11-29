package com.eliseu.BikesRegistration.repository;

import com.eliseu.BikesRegistration.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}
