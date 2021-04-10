package com.eliseu.BikesRegistration;

import com.eliseu.BikesRegistration.model.Bike;
import com.eliseu.BikesRegistration.repository.BikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.DELETE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BikeControllerAuthInMemoryTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private BikeRepository bikeRepository;
    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthentication("admin", "maisprati");
        }
    }

    @Before
    public void setup() {
        Bike bike = new Bike(1L, "Before's Bike", "BB", BigDecimal.valueOf(2598.30), LocalDate.of(2014, 10, 26), "Jonh", "Bikes Store");
        BDDMockito.when(bikeRepository.findById(bike.getId())).thenReturn(java.util.Optional.of(bike));
    }

    @Test
    public void listBikeWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
        System.out.println(port);
        restTemplate = restTemplate.withBasicAuth("1", "1");
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/bikes", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void getBikeByIdWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
        System.out.println(port);
        restTemplate = restTemplate.withBasicAuth("1", "1");
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/bikes/1", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void listBikesWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
        List<Bike> bikes = asList(new Bike(2L, "Jonh's Bike", "Caloi", BigDecimal.valueOf(850.30), LocalDate.of(2012, 12, 16), "Jonh", "Bikes Store"),
                new Bike(3L, "Marias's Bike", "MonarkCeci", BigDecimal.valueOf(1350.55), LocalDate.of(2014, 10, 26), "Maria", "Bikes Store"));
        BDDMockito.when(bikeRepository.findAll()).thenReturn(bikes);
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/bikes", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getBikeByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
        ResponseEntity<Bike> response = restTemplate.getForEntity("/v1/bikes/1", Bike.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getBikeByIdWhenUsernameAndPasswordAreCorrectAndBikeDoesNotExistShouldReturnStatusCode404() {
        ResponseEntity<Bike> response = restTemplate.getForEntity("/v1/bikes/{id}", Bike.class, -1);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void deleteWhenUserHasRoleAdminAndBikeExistsShouldReturnStatusCode200() {
        BDDMockito.doNothing().when(bikeRepository).deleteById(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/v1/bikes/{id}", DELETE, null, String.class, 1L);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @WithMockUser(username = "xx", password = "xx", roles = {"USER", "ADMIN"})
    public void deleteWhenUserHasRoleAdminAndBikeDoesNotExistsShouldReturnStatusCode404() throws Exception {
        BDDMockito.doNothing().when(bikeRepository).deleteById(1L);
//        ResponseEntity<String> exchange = restTemplate.exchange("/v1/admin/bikes/{id}", DELETE, null, String.class, -1L);
//        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/admin/bikes/{id}", -1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        System.out.println(MockMvcResultMatchers.status().toString());

    }

    @Test
    @WithMockUser(username = "xx", password = "xx", roles = {"USER"})
    public void deleteWhenUserDoesNotHaveHasRoleAdminReturnStatusCode403() throws Exception {
        BDDMockito.doNothing().when(bikeRepository).deleteById(1L);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/bikes/{id}", -1L))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void createWhenDescriptionIsNullShouldReturnStatusCode400BadRequest() {
        Bike bike = new Bike(3L, "", "MonarkCeci", BigDecimal.valueOf(1350.55), LocalDate.of(2014, 10, 26), "Maria", "Bikes Store");
        BDDMockito.when(bikeRepository.save(bike)).thenReturn(bike);
        ResponseEntity<String> response = restTemplate.postForEntity("/v1/bikes/", bike, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
//        Assertions.assertThat(response.getBody()).contains("fieldMessage", "O campo nome do estudante é obrigatório");
    }

    @Test
    public void createShouldPersistDataAndReturnStatusCode201() throws Exception {
        Bike bike = new Bike("Cecizinha", "MonarkCeci", BigDecimal.valueOf(1350.55), LocalDate.of(2014, 10, 26), "Maria", "Bikes Store");
        BDDMockito.when(bikeRepository.save(bike)).thenReturn(bike);
        ResponseEntity<Bike> responseTemp = restTemplate.getForEntity("/v1/bikes/3", Bike.class);//testestestesa
        System.out.println(responseTemp);
        System.out.println(bike);
        ResponseEntity<Bike> response = restTemplate.postForEntity("/v1/bikes/", bike, Bike.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
//        Assertions.assertThat(response.getBody().getId()).isNotNull();
    }


}
