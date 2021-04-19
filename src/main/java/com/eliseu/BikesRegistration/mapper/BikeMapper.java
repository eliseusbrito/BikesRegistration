package com.eliseu.BikesRegistration.mapper;

import com.eliseu.BikesRegistration.dto.BikeDto;
import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.model.Buyer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BikeMapper {



    public Bike toModel(Bike bike, BikeDto bikeDto, Buyer buyer) {
        int quantEdicaoCadastro = 0;
        bike.setDescription(bikeDto.getDescription());
        bike.setModel(bikeDto.getModel());
        bike.setPrice(bikeDto.getPrice());
        bike.setPurchaseDate(bikeDto.getPurchaseDate());
        bike.setBuyer(buyer);
        bike.setStore(bikeDto.getStore());
        bike.setCadastro(LocalDateTime.now());
        bike.setEdicaoCadastro(LocalDateTime.now());
        bike.setQuantEdicaoCadastro(quantEdicaoCadastro);
        return bike;
    }

//    public Integer buscarQuantEdicaoCadastro(){
//
//    }

}
