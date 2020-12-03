package com.eliseu.BikesRegistration.service;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.repository.BikeRepository;
import com.eliseu.BikesRegistration.service.exceptions.ResourceFoundException;
import com.eliseu.BikesRegistration.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Bike insert(Bike bike) {
        List<Bike> bikes = bikeRepository.findByDescription(bike.getDescription());
        if (bikes.size() > 0) {
            throw new ResourceFoundException(bike.getDescription());
        }
        return bikeRepository.save(bike);
    }

    public void delete(Long id) {
        try{
            bikeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Bike update(Long id, Bike bike) {
        try {
            Bike entity = bikeRepository.getOne(id);
            entity.setDescription(bike.getDescription());
            entity.setModel(bike.getModel());
            entity.setPrice(bike.getPrice());
            entity.setPurchaseDate(bike.getPurchaseDate());
            entity.setBuyerName(bike.getBuyerName());
            entity.setStore(bike.getStore());
            return bikeRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Bike updatePatch(Long id, Bike bike) {
        try{
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
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
