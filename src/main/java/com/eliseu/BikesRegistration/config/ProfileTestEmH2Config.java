package com.eliseu.BikesRegistration.config;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.model.Buyer;
import com.eliseu.BikesRegistration.repository.BikeRepository;
import com.eliseu.BikesRegistration.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("testEmH2")
public class ProfileTestEmH2Config implements CommandLineRunner {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public void run(String... args) throws Exception {
        LocalDate localDate = LocalDate.now();
        LocalDate d1 = LocalDate.of(1990, 10, 8);
        LocalDate d2 = LocalDate.of(2021, 02, 27);
        Buyer mario = new Buyer("Mario","Silva","mario@gmail.com","35955885",LocalDate.of(1990, 01, 26), LocalDateTime.now());
        Buyer davi = new Buyer("Davi","Valente","davi@gmail.com","35955885",LocalDate.of(2010, 11, 20), LocalDateTime.now());
        Bike b1 = new Bike("Bicicleta de Infancia", "Monareta", BigDecimal.valueOf(800.35), d1, mario, "MachadoBikes", LocalDateTime.now(), LocalDateTime.now(), 0);
        Bike b2 = new Bike("Bicicleta Atual", "Caloi", BigDecimal.valueOf(645.94), d2, davi, "CésarBikes", LocalDateTime.now(), LocalDateTime.now(), 0);
        Bike b3 = new Bike("Bicicleta dos Sonhos", "Star", BigDecimal.valueOf(5786.55), LocalDate.of(2020, 11, 20), davi, "WorldBikes", LocalDateTime.now(), LocalDateTime.now(), 0);
        buyerRepository.saveAll(Arrays.asList(mario, davi));
        bikeRepository.saveAll(Arrays.asList(b1, b2, b3));
    }
}
