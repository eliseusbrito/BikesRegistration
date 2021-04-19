package com.eliseu.BikesRegistration.service;

import com.eliseu.BikesRegistration.dto.BuyerDto;
import com.eliseu.BikesRegistration.mapper.BuyerMapper;
import com.eliseu.BikesRegistration.model.Buyer;
import com.eliseu.BikesRegistration.repository.BuyerRepository;
import com.eliseu.BikesRegistration.service.exceptions.ResourceFoundException;
import com.eliseu.BikesRegistration.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    public BuyerRepository buyerRepository;

    public final BuyerMapper buyerMapper;

    public BuyerService(BuyerMapper buyerMapper) {
        this.buyerMapper = buyerMapper;
    }

    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    public Buyer findById(Long id) {
        Optional<Buyer> obj = buyerRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Buyer insert(BuyerDto buyerDto) {
        List<Buyer> buyers = buyerRepository.findByNome(buyerDto.getNome());
        if (buyers.size() > 0) {
            throw new ResourceFoundException(buyerDto.getNome());
        }
        Buyer buyer = new Buyer();
        buyer = buyerMapper.toModel(buyer, buyerDto);
        return buyerRepository.save(buyer);
    }

    public void delete(Long id) {
        try {
            buyerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Buyer update(Long id, Buyer buyer) {
        try {
            Buyer entity = buyerRepository.getOne(id);
            entity.setNome(buyer.getNome());
            entity.setSobrenome(buyer.getSobrenome());
            entity.setEmail(buyer.getEmail());
            entity.setTelefone(buyer.getTelefone());
            entity.setDataCadastro(entity.getDataCadastro());
            entity.setAtualizacaoCadastro(LocalDateTime.now());
            return buyerRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
