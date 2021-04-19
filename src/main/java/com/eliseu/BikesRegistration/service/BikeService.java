package com.eliseu.BikesRegistration.service;

import com.eliseu.BikesRegistration.dto.BikeDto;
import com.eliseu.BikesRegistration.mapper.BikeMapper;
import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.model.Buyer;
import com.eliseu.BikesRegistration.repository.BikeRepository;
import com.eliseu.BikesRegistration.repository.BuyerRepository;
import com.eliseu.BikesRegistration.service.exceptions.ResourceFoundException;
import com.eliseu.BikesRegistration.service.exceptions.ResourceNotFoundException;
import com.eliseu.BikesRegistration.service.exceptions.UnnecessaryUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    @Autowired
    public BikeRepository bikeRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    public final BikeMapper bikeMapper;

    public BikeService(BikeMapper bikeMapper) {
        this.bikeMapper = bikeMapper;
    }

    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }

    public Bike findById(Long id) {
        Optional<Bike> obj = bikeRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Bike insert(BikeDto bikeDto) {
        List<Bike> bikes = bikeRepository.findByDescription(bikeDto.getDescription());
        if (bikes.size() > 0) {
            throw new ResourceFoundException(bikeDto.getDescription());
        }
        Bike bike = new Bike();
        Buyer buyer = buyerRepository.findById(bikeDto.getIdBuyer()).get();
        bike = bikeMapper.toModel(bike, bikeDto, buyer);
        return bikeRepository.save(bike);
    }

    public void delete(Long id) {
        try {
            bikeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Bike update(Long id, BikeDto bikeDto) {
        try {
            Bike entity = bikeRepository.getOne(id);
            entity.setDescription(bikeDto.getDescription());
            entity.setModel(bikeDto.getModel());
            entity.setPrice(bikeDto.getPrice());
            entity.setPurchaseDate(bikeDto.getPurchaseDate());
            entity.setBuyer(buyerRepository.findById(bikeDto.getIdBuyer()).get());
            entity.setStore(bikeDto.getStore());
            entity.setCadastro(entity.getCadastro());
            entity.setEdicaoCadastro(LocalDateTime.now());
            entity.setQuantEdicaoCadastro(entity.getQuantEdicaoCadastro()+1);
            return bikeRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Bike updatePatch(Long id, BikeDto bikeDto) {
        Boolean updated = false;
        try {
            Bike entity = bikeRepository.getOne(id);
            if (bikeDto.getDescription() != null && !entity.getDescription().equals(bikeDto.getDescription())) {
                entity.setDescription(bikeDto.getDescription());
                updated = true;
            }
            if (bikeDto.getModel() != null && !entity.getModel().equals(bikeDto.getModel())) {
                entity.setModel(bikeDto.getModel());
                updated = true;
            }
            if (bikeDto.getPrice() != null && !entity.getPrice().equals(bikeDto.getPrice())) {
                entity.setPrice(bikeDto.getPrice());
                updated = true;
            }
            if (bikeDto.getPurchaseDate() != null && !entity.getPurchaseDate().equals(bikeDto.getPurchaseDate())) {
                entity.setPurchaseDate(bikeDto.getPurchaseDate());
                updated = true;
            }
            if (bikeDto.getIdBuyer() != null && !entity.getBuyer().getId().equals(bikeDto.getIdBuyer())) {
                entity.setBuyer(buyerRepository.findById(bikeDto.getIdBuyer()).get());
                updated = true;
            }
            if (bikeDto.getStore() != null && !entity.getStore().equals(bikeDto.getStore())) {
                entity.setStore(bikeDto.getStore());
                updated = true;
            }
            if (updated == true) {
                entity.setEdicaoCadastro(LocalDateTime.now());
                entity.setQuantEdicaoCadastro(entity.getQuantEdicaoCadastro()+1);
            } else {
                throw new UnnecessaryUpdateException(entity.getId());
            }
            return bikeRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
