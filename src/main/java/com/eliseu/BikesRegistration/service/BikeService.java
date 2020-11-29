package com.eliseu.BikesRegistration.service;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    @Autowired
    public BikeRepository bikeRepository;

    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }

    public Bike findById(Long id) {
        Optional<Bike> obj = bikeRepository.findById(id);
        return obj.orElseThrow();
    }

    public Bike insert(Bike bike) {
        return bikeRepository.save(bike);
    }

    public void delete(Long id) {
        bikeRepository.deleteById(id);
    }

    public Bike update(Long id, Bike bike) {
        Bike entity = bikeRepository.getOne(id);
        entity.setDescription(bike.getDescription());
        entity.setModel(bike.getModel());
        entity.setPrice(bike.getPrice());
        entity.setPurchaseDate(bike.getPurchaseDate());
        entity.setBuyerName(bike.getBuyerName());
        entity.setStore(bike.getStore());
        return bikeRepository.save(entity);
    }

    public Bike updatePatch(Long id, Bike bike) {
        Bike entity = bikeRepository.getOne(id);
        if (bike.getDescription() != null) {
            entity.setDescription(bike.getDescription());
        }
        if (bike.getModel() != null) {
            entity.setModel(bike.getModel());
        }
        if (bike.getPrice() != null) {
            entity.setPrice(bike.getPrice());
        }
        if (bike.getPurchaseDate() != null) {
            entity.setPurchaseDate(bike.getPurchaseDate());
        }
        if (bike.getBuyerName() != null) {
            entity.setBuyerName(bike.getBuyerName());
        }
        if (bike.getStore() != null) {
            entity.setStore(bike.getStore());
        }
        return bikeRepository.save(entity);
    }

}
