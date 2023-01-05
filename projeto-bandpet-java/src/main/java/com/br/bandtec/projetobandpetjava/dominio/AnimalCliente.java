package com.br.bandtec.projetobandpetjava.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AnimalCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;
    @ManyToOne
//    @JsonIgnore
    private ClientesPetShop fkCliente;
    private String nomeAnimal;
    private String raca;
    private String porte;
    private String tipo;
    private LocalDate dataNasc;
    private String genero;


//        private List<ClientesPetShop> clientes;
//
//        public List<ClientesPetShop> getClientes() {
//            return clientes;
//        }
//
//        public void setClientes(List<ClientesPetShop> clientes) {
//            this.clientes = clientes;
//        }

//    private List<ClientesPetShop> clientes;
//
//    public List<ClientesPetShop> getClientes() {
//        return clientes;
//    }
//
//    public void setClientes(List<ClientesPetShop> clientes) {
//        this.clientes = clientes;
//    }


    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public ClientesPetShop getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(ClientesPetShop fkCliente) {
        this.fkCliente = fkCliente;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
