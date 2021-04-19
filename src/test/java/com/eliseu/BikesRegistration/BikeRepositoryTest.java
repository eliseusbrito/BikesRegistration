package com.eliseu.BikesRegistration;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.model.Buyer;
import com.eliseu.BikesRegistration.repository.BikeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BikeRepositoryTest {

    @Autowired
    private BikeRepository bikeRepository;

    @Test
    public void createdShouldPersistData() {
        Buyer buyer = new Buyer();
        Bike bike = new Bike("Jonh's Bike", "MonarkCeci", BigDecimal.valueOf(1350.55), LocalDate.of(2014, 10, 26), buyer, "Bikes Store", LocalDateTime.now(), LocalDateTime.now(), 0);
        this.bikeRepository.save(bike);
        assertThat(bike.getId()).isNotNull();
        assertThat(bike.getDescription()).isEqualTo("Jonh's Bike");
        assertThat(bike.getPrice()).isEqualTo(1350.55);
        assertThat(bike.getPurchaseDate()).isEqualTo(LocalDate.of(2014, 10, 26));
    }

    @Test
    public void deleteShouldRemoveData() {
        Buyer buyer = new Buyer();
        Bike bike = new Bike("Jonh's Bike", "MonarkCeci", BigDecimal.valueOf(1350.55), LocalDate.of(2014, 10, 26), buyer, "Bikes Store",LocalDateTime.now(), LocalDateTime.now(), 0);
        this.bikeRepository.save(bike);
        bikeRepository.delete(bike);
        assertThat(bikeRepository.findById(bike.getId())).isEmpty();
    }

    @Test
    public void updateShouldChangeAndPersistData() {
        Buyer buyer = new Buyer();
        Bike bike = new Bike("Jonh's Bike", "MonarkCeci", BigDecimal.valueOf(1350.55), LocalDate.of(2014, 10, 26), buyer, "Bikes Store",LocalDateTime.now(), LocalDateTime.now(), 0);
        this.bikeRepository.save(bike);
        bike.setDescription("New Description");
        bike.setModel("New Model");
        this.bikeRepository.save(bike);
        assertThat(bike.getDescription()).isEqualTo("New Description");
        assertThat(bike.getModel()).isEqualTo("New Model");
    }

    @Test
    public void findByDescription() {
        Buyer buyer = new Buyer();
        Bike bike = new Bike("Adriam's Bike", "MonarkCeci", BigDecimal.valueOf(1350.55), LocalDate.of(2014, 10, 26), buyer, "Bikes Store",LocalDateTime.now(), LocalDateTime.now(), 0);
        this.bikeRepository.save(bike);
        List<Bike> bikeList = bikeRepository.findByDescription("Adriam's Bike");
        assertThat(bikeList.size()).isEqualTo(1);
    }

}
