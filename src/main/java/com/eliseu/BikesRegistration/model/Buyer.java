package com.eliseu.BikesRegistration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_buyer")
public class Buyer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nome;

    String sobrenome;

    String email;

    String telefone;

    LocalDate dataCadastro;

    LocalDateTime atualizacaoCadastro;

    @JsonIgnore
    @OneToMany(mappedBy="buyer")
    private List<Bike> bikes = new ArrayList<>();

    public Buyer() {
    }

    public Buyer(String nome, String sobrenome, String email, String telefone, LocalDate dataCadastro, LocalDateTime atualizacaoCadastro, List<Bike> bikes) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.atualizacaoCadastro = atualizacaoCadastro;
        this.bikes = bikes;
    }

    public Buyer(String nome, String sobrenome, String email, String telefone, LocalDate dataCadastro, LocalDateTime atualizacaoCadastro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.atualizacaoCadastro = atualizacaoCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getAtualizacaoCadastro() {
        return atualizacaoCadastro;
    }

    public void setAtualizacaoCadastro(LocalDateTime atualizacaoCadastro) {
        this.atualizacaoCadastro = atualizacaoCadastro;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", atualizacaoCadastro=" + atualizacaoCadastro +
                ", bikes=" + bikes +
                '}';
    }
}
