package com.br.bandtec.projetobandpetjava.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class AnimaisServicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimalServico;
    @ManyToOne
//    @JsonIgnore
    private OrdemServico fkOrdemServico;
    @ManyToOne
//    @JsonIgnore
    private Servico fkServico;

    public Integer getIdAnimalServico() {
        return idAnimalServico;
    }

    public void setIdAnimalServico(Integer idAnimalServico) {
        this.idAnimalServico = idAnimalServico;
    }

    public OrdemServico getFkOrdemServico() {
        return fkOrdemServico;
    }

    public void setFkOrdemServico(OrdemServico fkOrdemServico) {
        this.fkOrdemServico = fkOrdemServico;
    }

    public Servico getFkServico() {
        return fkServico;
    }

    public void setFkServico(Servico fkServico) {
        this.fkServico = fkServico;
    }
}
