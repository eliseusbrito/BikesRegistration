package com.eliseu.BikesRegistration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_bike")
public class Bike implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String model;

    private BigDecimal price;

    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name="buyer_id")
    private Buyer buyer;
    
    private String store;

    private LocalDateTime cadastro;

    private LocalDateTime edicaoCadastro;

    private int quantEdicaoCadastro;

    public Bike() {
    }

    public Bike(Long id, String description, String model, BigDecimal price, LocalDate purchaseDate, Buyer buyer, String store, LocalDateTime cadastro, LocalDateTime edicaoCadastro, int quantEdicaoCadastro) {
        this.id = id;
        this.description = description;
        this.model = model;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.buyer = buyer;
        this.store = store;
        this.cadastro = cadastro;
        this.edicaoCadastro = edicaoCadastro;
        this.quantEdicaoCadastro = quantEdicaoCadastro;
    }

    public Bike(String description, String model, BigDecimal price, LocalDate purchaseDate, Buyer buyer, String store, LocalDateTime cadastro, LocalDateTime edicaoCadastro, int quantEdicaoCadastro) {
        this.description = description;
        this.model = model;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.buyer = buyer;
        this.store = store;
        this.cadastro = cadastro;
        this.edicaoCadastro = edicaoCadastro;
        this.quantEdicaoCadastro = quantEdicaoCadastro;
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

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public LocalDateTime getCadastro() {
        return cadastro;
    }

    public void setCadastro(LocalDateTime cadastro) {
        this.cadastro = cadastro;
    }

    public LocalDateTime getEdicaoCadastro() {
        return edicaoCadastro;
    }

    public void setEdicaoCadastro(LocalDateTime edicaoCadastro) {
        this.edicaoCadastro = edicaoCadastro;
    }

    public int getQuantEdicaoCadastro() {
        return quantEdicaoCadastro;
    }

    public void setQuantEdicaoCadastro(int quantEdicaoCadastro) {
        this.quantEdicaoCadastro = quantEdicaoCadastro;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", buyer=" + buyer +
                ", store='" + store + '\'' +
                ", cadastro=" + cadastro +
                ", edicaoCadastro=" + edicaoCadastro +
                ", quantEdicaoCadastro=" + quantEdicaoCadastro +
                '}';
    }

}
