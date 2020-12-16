package com.eliseu.BikesRegistration.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "tb_bike")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotNull(message = "Price is mandatory")
    @Positive
    private Double price;
    @NotNull(message = "PurchaseDate is mandatory")
    @Past
    private LocalDate purchaseDate;
    @NotBlank(message = "BuyerName is mandatory")
    private String buyerName;
    @NotBlank(message = "Store is mandatory")
    private String store;

    public Bike() {
    }

    public Bike(String description, String model, Double price, LocalDate purchaseDate, String buyerName, String store) {
        this.description = description;
        this.model = model;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.buyerName = buyerName;
        this.store = store;
    }

    public Bike(Long id, @NotBlank(message = "Description is mandatory") String description, @NotBlank(message = "Model is mandatory") String model, @NotNull(message = "Price is mandatory") @Positive Double price, @NotNull(message = "PurchaseDate is mandatory") @Past LocalDate purchaseDate, @NotBlank(message = "BuyerName is mandatory") String buyerName, @NotBlank(message = "Store is mandatory") String store) {
        this.id = id;
        this.description = description;
        this.model = model;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.buyerName = buyerName;
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Bikes{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", buyerName='" + buyerName + '\'' +
                ", store='" + store + '\'' +
                '}';
    }

}
