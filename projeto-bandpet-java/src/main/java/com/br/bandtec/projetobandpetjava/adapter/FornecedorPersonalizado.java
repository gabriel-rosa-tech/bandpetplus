package com.br.bandtec.projetobandpetjava.adapter;

import com.br.bandtec.projetobandpetjava.dominio.Fornecedor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

public class FornecedorPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonalizado;
    private String nomeFornecedor;
    private String descricao;
    private List<String> fornecedores;

//    public FornecedorPersonalizado(Fornecedor entidade) {
//        this.nomeFornecedor = entidade.getEmpresa();
//        this.descricao = entidade.getDescricao();
//        this.fornecedores = entidade.getEmpresa().stream().map(Fornecedor::getEmpresa).collect(Collectors.toList());
//        /*
//No código acima usamos o "stream()" que é um recurso que nasceu no Java 8.
//Ele permite executar várias ações interessantes e de forma simplificadaem coleçõess,
//como filtros, ordenações, transformações etc
//No exemplo acima fizemos uma transformação: criamos uma lista de String a partir do "getNome()" de cada Dragao na lista
//.map(Dragao::getNome) -> Pedimos para o Java usar o getNome() de cada Dragao da lista
//.collect(Collectors.toList()) -> Pedimos para ele usar todos esses getNome() para montar uma lista de String
//
//A linha 14 faz o mesmo que a sequência de linhas comentadas abaixo
//         */
//        /*
//        this.dragoes = new ArrayList<>();
//        for (Dragao dragao : entidade.getDragoes()) {
//            this.dragoes.add(dragao.getNome());
//        }
//        */
//    }
    public Integer getIdPersonalizado() {
        return idPersonalizado;
    }

    public void setIdPersonalizado(Integer idPersonalizado) {
        this.idPersonalizado = idPersonalizado;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
