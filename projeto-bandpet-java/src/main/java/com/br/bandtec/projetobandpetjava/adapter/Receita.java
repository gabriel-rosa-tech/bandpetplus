package com.br.bandtec.projetobandpetjava.adapter;

public class Receita {

    private Double receita;
    private Double ticketMedio;

    public Double getReceita() {
        return receita;
    }

    public void setReceita(Double receita) {
        this.receita = receita;
    }

    public Double getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(Double ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public Receita(Double receita, Double ticketMedio) {
        this.receita = receita;
        this.ticketMedio = ticketMedio;
    }
}
