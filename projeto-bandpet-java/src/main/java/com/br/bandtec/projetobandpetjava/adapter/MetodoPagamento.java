package com.br.bandtec.projetobandpetjava.adapter;

public class MetodoPagamento {

    private String metodo;
    private Double valor;

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public MetodoPagamento(String metodo, Double valor) {
        this.metodo = metodo;
        this.valor = valor;
    }
}
