package com.br.bandtec.projetobandpetjava.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClientesPetShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    @ManyToOne
    private PetShop fkPetshop;
    private String nomeCliente;
    private Integer servicoUsado;
    private String telefoneCelular;
    private String telefoneResidencial;
    private String endereco;
    private Integer numero;
    private String complemento;

//    private List<AnimalCliente> animal;
//
//    public List<AnimalCliente> getAnimal() {
//        return animal;
//    }
//
//    public void setAnimal(List<AnimalCliente> animal) {
//        this.animal = animal;
//    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public PetShop getFkPetshop() {
        return fkPetshop;
    }

    public void setFkPetshop(PetShop fkPetshop) {
        this.fkPetshop = fkPetshop;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getServicoUsado() {
        return servicoUsado;
    }

    public void setServicoUsado(Integer servicoUsado) {
        this.servicoUsado = servicoUsado;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
