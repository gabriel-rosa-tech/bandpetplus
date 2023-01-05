package com.br.bandtec.projetobandpetjava.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCusto;
    @ManyToOne
//    @JsonIgnore
    private PetShop fkPetshop;
    private String descricao;
    private double valor;
    private String dataConsumo;

    public Integer getIdCusto() {
        return idCusto;
    }

    public void setIdCusto(Integer idCusto) {
        this.idCusto = idCusto;
    }

    public PetShop getFkPetshop() {
        return fkPetshop;
    }

    public void setFkPetshop(PetShop fkPetshop) {
        this.fkPetshop = fkPetshop;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(String dataConsumo) {
        this.dataConsumo = dataConsumo;
    }
}
