package com.eliseu.BikesRegistration.config;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("testEmH2")
public class ProfileTestEmH2Config implements CommandLineRunner {

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public void run(String... args) throws Exception {
        LocalDate localDate = LocalDate.now();
        LocalDate d1 = LocalDate.of(1990, 10, 8);
        Bike b1 = new Bike("Bicicleta de Infancia", "Monareta", 800.00, d1, "Eliseu", "MachadoBikes");
        Bike b2 = new Bike("Bicicleta Atual", "Caloi", 800.00, localDate, "Eliseu", "CésarBikes");
        Bike b3 = new Bike("Bicicleta dos Sonhos", "Star", 5786.00, LocalDate.of(2020, 11, 20), "Davi", "WorldBikes");
        bikeRepository.saveAll(Arrays.asList(b1, b2, b3));
    }
}
