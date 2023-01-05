package com.br.bandtec.projetobandpetjava.adapter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class ControlePersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idControlesPersonalizado;
    private String nomeCliente;
    private String nomePet;
    private LocalDateTime entrada;
    private LocalDateTime saída;
    private Double valor;
    private String metodoPagamento;

    public Integer getIdControlesPersonalizado() {
        return idControlesPersonalizado;
    }

    public void setIdControlesPersonalizado(Integer idControlesPersonalizado) {
        this.idControlesPersonalizado = idControlesPersonalizado;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaída() {
        return saída;
    }

    public void setSaída(LocalDateTime saída) {
        this.saída = saída;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
