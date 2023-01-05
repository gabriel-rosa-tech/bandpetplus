package com.br.bandtec.projetobandpetjava.dominio;

import javax.persistence.*;

@Entity
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeArquivo;
    /*
    Quando queremos armazenar conteúdos de arquivos em tabelas do banco
    criamos um campo do tipo byte[] (vetor de bytes)
    Nos bancos de dados, isso muda, pois varia de acordo com o fabricante. Exemplos:
    MySQL: BLOB e CLOB
    SQL Server: binary
    PostgreSQL: bytea

    @Column(length = 5_000_000) -> aqui dizemos que o campo terá 5.000.000 de bytes (5Mb) de tamanho máximo
     */
    @Column(length = 5_000_000)
    private byte[] conteudoArquivo;

    private String tipoArquivo;

    // criar getters e setters


    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public byte[] getConteudoArquivo() {
        return conteudoArquivo;
    }

    public void setConteudoArquivo(byte[] conteudoArquivo) {
        this.conteudoArquivo = conteudoArquivo;
    }
}