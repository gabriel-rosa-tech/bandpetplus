package com.br.bandtec.projetobandpetjava.adapter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CustoPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustoPersonalizado;
    private String descricaoCusto;
    private Double valor;

    public Integer getIdCustoPersonalizado() {
        return idCustoPersonalizado;
    }

    public void setIdCustoPersonalizado(Integer idCustoPersonalizado) {
        this.idCustoPersonalizado = idCustoPersonalizado;
    }

    public String getDescricaoCusto() {
        return descricaoCusto;
    }

    public void setDescricaoCusto(String descricaoCusto) {
        this.descricaoCusto = descricaoCusto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
