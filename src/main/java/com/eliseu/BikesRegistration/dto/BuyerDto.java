package com.eliseu.BikesRegistration.dto;

import javax.validation.constraints.NotBlank;

public class BuyerDto {

    @NotBlank
    String nome;

    @NotBlank
    String sobrenome;

    String email;

    @NotBlank
    String telefone;

    public BuyerDto() {
    }

    public BuyerDto(@NotBlank String nome, @NotBlank String sobrenome, String email, @NotBlank String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
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
}
