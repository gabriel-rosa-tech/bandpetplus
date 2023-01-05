package com.br.bandtec.projetobandpetjava.dominio;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class    ListaObj <T>  {
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tam) {
        vetor = (T[]) new Object[tam];
        nroElem = 0;
    }

    public Boolean adiciona(T elemento){
        if(vetor.length > nroElem){
            vetor[nroElem++] = elemento;
            System.out.println("Elemento foi adicionado!!!");
            return true;
        }
        System.out.println("Não foi possivel inserir :#");
        return false;
    }

    public void exibe(){
        if(nroElem ==0){
            System.out.println("A lista está vazia");
        }else{
            for (int i = 0; i < nroElem; i++){
                System.out.println("\nIndice["+ i +"]: "+ vetor[i]);
            }
        }
    }

    public Integer buscaItem(T procurado){
        for (int i = 0; i < vetor.length; i++){
            if(vetor[i].equals(procurado)){
                System.out.println("O item procurado está no índice "+i);
                return i;
            }
        }
        System.out.println("O item procurado não foi encontrado");
        return -1;
    }

    public Boolean removePeloIndice(int indice){
        if(0 > indice || indice > nroElem){
            System.out.println("Número é inválido para realizar a busca");
            return false;
        }
        else {
            for (int i = indice; i < nroElem-1;){
                vetor[i] = vetor[++i];
            }
            System.out.println("Item removido");
            nroElem--;
            return true;
        }
    }

    public Boolean removeElemento(T procurado){
        return removePeloIndice(buscaItem(procurado));
    }

    public Integer getTamanho(){
        System.out.println("Tamanho lista: "+ nroElem);
        return nroElem;
    }

    public T getElemento(int indice){
        if(indice < 0 || indice >= nroElem){
            System.out.println("Elemento não encontrado");
            return null;
        }
        else {
            System.out.println("Elemento escolhido "+ vetor[indice]);
            return (T) vetor[indice];
        }
    }

    public void limpa(){
        nroElem = 0;
        System.out.println("Tamanho da lista "+ nroElem);
    }

}
