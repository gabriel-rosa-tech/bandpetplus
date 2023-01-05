package com.br.bandtec.projetobandpetjava.dominio;

import javax.persistence.*;

//@SqlResultSetMapping(name = "nomeDaQueryNativa",
//        classes = @ConstructorResult(targetClass = OrdemGraficoPie.class,
//                columns = {
//                        @ColumnResult(name = "valorTotal", type = Double.class),
//                        @ColumnResult(name = "metodoPagamento", type = String.class)
//                }))
//
//@NamedNativeQuery(name = "OrdemServico.grupByMetodoPagamento",
//        resultSetMapping = "nomeDaQueryNativa",
//        query = "SELECT SUM(valor_pago) as valorTotal, metodo_pagamento as metodoPagamento " +
//                "from ordem_servico as ordem\n" +
//                "\tJOIN animal_cliente as a on  a.id_animal = ordem.fk_animal_cliente_id_animal \n" +
//                "\tJOIN clientes_pet_shop as cli on a.fk_cliente_id_cliente = cli.id_cliente \n" +
//                "\tJOIN pet_shop as pet on cli.fk_petshop_id_petshop = pet.id_petshop\n" +
//                "\tWhere pet.id_petshop=?1 AND hora_entrada BETWEEN ?2 and ?3\t\n" +
//                "\tGROUP BY metodo_pagamento;")


@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdemServico;
    @ManyToOne
//    @JsonIgnore
    private AnimalCliente fkAnimalCliente;
    private String horaEntrada;
    private String horaSaida;
    private String metodoPagamento;
    private Double valorPago;





    public Integer getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(Integer idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public AnimalCliente getFkAnimalCliente() {
        return fkAnimalCliente;
    }

    public void setFkAnimalCliente(AnimalCliente fkAnimalCliente) {
        this.fkAnimalCliente = fkAnimalCliente;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
