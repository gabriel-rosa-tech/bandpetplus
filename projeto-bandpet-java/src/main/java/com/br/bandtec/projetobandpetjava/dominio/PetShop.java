package com.br.bandtec.projetobandpetjava.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PetShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPetshop;
    private String nomePetshop;
    private String email;
    private String senha;
    private String cnpj;
    private String assinaturaEletronica; // String passar
    private Integer cartaoLigado;
    private Integer limiteFidelidade;
    private String telefone;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String endereco;
    private Integer numero;
    private String complemento;

    public Integer getIdPetshop() {
        return idPetshop;
    }

    public void setIdPetshop(Integer idPetshop) {
        this.idPetshop = idPetshop;
    }

    public String getNomePetShop() {
        return nomePetshop;
    }

    public void setNomePetShop(String nomePetshop) {
        this.nomePetshop = nomePetshop;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomePetshop() {
        return nomePetshop;
    }

    public void setNomePetshop(String nomePetshop) {
        this.nomePetshop = nomePetshop;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAssinaturaEletronica() {
        return assinaturaEletronica;
    }

    public void setAssinaturaEletronica(String assinaturaEletronica) {
        this.assinaturaEletronica = assinaturaEletronica;
    }

    public Integer getCartaoLigado() {
        return cartaoLigado;
    }

    public void setCartaoLigado(Integer cartaoLigado) {
        this.cartaoLigado = cartaoLigado;
    }

    public Integer getLimiteFidelidade() {
        return limiteFidelidade;
    }

    public void setLimiteFidelidade(Integer limiteFidelidade) {
        this.limiteFidelidade = limiteFidelidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /*
        {
        "assinaturaEletronica": 123456,
        "cartaoLigado": 1,
        "cnpj": "15425454",
        "complemento": "",
        "email": "admin@admin.com",
        "endereco": "Rua Prates",
        "limiteFidelidade": 5,
        "nomePetShop": "Amigãozão",
        "nomeResponsavel": "Gabriel",
        "numero": 64,
        "senha": "admin123",
        "telefone": "11 999999898",
        "telefoneResponsavel": "11 988888989"
        }
    */
}
