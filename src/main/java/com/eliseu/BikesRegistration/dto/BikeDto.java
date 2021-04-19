package com.eliseu.BikesRegistration.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BikeDto {

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotNull(message = "Price is mandatory")
    @Positive
    private BigDecimal price;

    @NotNull(message = "PurchaseDate is mandatory")
    @Past
    private LocalDate purchaseDate;

    private Long idBuyer;

    @NotBlank(message = "Store is mandatory")
    private String store;

    public BikeDto() {
    }

    public BikeDto(@NotBlank(message = "Description is mandatory") String description, @NotBlank(message = "Model is mandatory") String model, @NotNull(message = "Price is mandatory") @Positive BigDecimal price, @NotNull(message = "PurchaseDate is mandatory") @Past LocalDate purchaseDate, Long idBuyer, @NotBlank(message = "Store is mandatory") String store) {
        this.description = description;
        this.model = model;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.idBuyer = idBuyer;
        this.store = store;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(Long idBuyer) {
        this.idBuyer = idBuyer;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
