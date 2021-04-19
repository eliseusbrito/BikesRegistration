package com.eliseu.BikesRegistration.mapper;

import com.eliseu.BikesRegistration.dto.BikeDto;
import com.eliseu.BikesRegistration.dto.BuyerDto;
import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.model.Buyer;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class BuyerMapper {

    public Buyer toModel(Buyer buyer, BuyerDto buyerDto) {
        buyer.setNome(buyerDto.getNome());
        buyer.setSobrenome(buyerDto.getSobrenome());
        buyer.setEmail(buyerDto.getEmail());
        buyer.setTelefone(buyerDto.getTelefone());
        buyer.setDataCadastro(LocalDate.now());
        buyer.setAtualizacaoCadastro(LocalDateTime.now());
        return buyer;
    }


}
