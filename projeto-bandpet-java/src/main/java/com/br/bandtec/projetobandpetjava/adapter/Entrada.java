package com.br.bandtec.projetobandpetjava.adapter;

public class Entrada {

    private Double receitaBruta;
    private Integer qtdeAnimais;
    private Double valorMin;
    private Double valorMax;

    public Entrada(Double receitaBruta, Integer qtdeAnimais, Double valorMin, Double valorMax) {
        this.receitaBruta = receitaBruta;
        this.qtdeAnimais = qtdeAnimais;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }

    @Override
    public String toString() {
        return "{" +
                "receitaBruta:" + receitaBruta +
                ", qtdeAnimais:" + qtdeAnimais +
                ", valorMin:" + valorMin +
                ", valorMax:" + valorMax +
                '}';
    }
}
