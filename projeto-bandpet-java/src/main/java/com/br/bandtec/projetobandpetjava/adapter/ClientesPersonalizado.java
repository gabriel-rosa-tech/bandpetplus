//package com.br.bandtec.projetobandpetjava.adapter;
//
//import com.br.bandtec.projetobandpetjava.dominio.AnimalCliente;
//import com.br.bandtec.projetobandpetjava.dominio.ClientesPetShop;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ClientesPersonalizado {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer idClientesPersonalizado;
//
//    private String nomeCliente;
//    private String nomePet;
//
//    {
//        List<String> animal = Arrays.asList(
//                new AnimalCliente().getFkCliente().getNomeCliente(),
//                new AnimalCliente().getNomeAnimal()
//        );
//
//
//        animal.stream().filter(animal -> animal.)
//    }
////    private List<String> clientes;
////    private List<String> animal;
//
//
//    public void populaClientes(List<ClientesPetShop> clientes){
//
//    }
////
////    public ClientesPersonalizado(AnimalCliente entidade) {
////        this.nomeCliente = entidade.getFkCliente().getNomeCliente();
////        this.nomePet = entidade.getNomeAnimal();
////
////
////        this.clientes = entidade.getClientes().stream().map(ClientesPetShop::getNomeCliente).collect(Collectors.toList());
////        this.animal = entidade.getClientes().stream().map(AnimalCliente::getNomeAnimal).collect(Collectors.toList());
////    }
//
////    public ClientesPersonalizadoSimplesResposta(ClientesPetShop entidade) {
////        this.nomeCliente = entidade.getNomeCliente();
////        this.nomePet = entidade.getNomeCliente().get;
////
////        this.dragoes = entidade.getDragoes().stream().map(Dragao::getNome).collect(Collectors.toList());
////    }
//
//
////    { List<ClientesPetShop> clientes = new ArrayList<>();
////
////      List<ClientesPetShop> clientesOrdenadosPeloNome =
////                clientes.stream().sorted(Comparator.comparing(ClientesPetShop::getNomeCliente)).collect(Collectors.toList());
////
////    }
//
//
//    public String getNomeCliente() {
//        return nomeCliente;
//    }
//
//    public void setNomeCliente(String nomeCliente) {
//        this.nomeCliente = nomeCliente;
//    }
//
//    public String getNomePet() {
//        return nomePet;
//    }
//
//    public void setNomePet(String nomePet) {
//        this.nomePet = nomePet;
//    }
//}
//
////    private String nomeCliente;
////    private String nomePet;
////    private List<String> clientes;
////    private List<String> animal;
////
////
////    public ClientesPersonalizado(AnimalCliente entidade) {
////        this.nomeCliente = entidade.getFkCliente().getNomeCliente();
////        this.nomePet = entidade.getNomeAnimal();
////
////
////        this.clientes = entidade.getClientes().stream().map(ClientesPetShop::getNomeCliente).collect(Collectors.toList());
////        //this.animal = entidade.getClientes().stream().map(AnimalCliente::getNomeAnimal).collect(Collectors.toList());
////    }
////
////
////    { List<ClientesPetShop> clientes = new ArrayList<>();
////
////      List<ClientesPetShop> clientesOrdenadosPeloNome =
////                clientes.stream().sorted(Comparator.comparing(ClientesPetShop::getNomeCliente)).collect(Collectors.toList());
////
////    }
////
////
////    public String getNomeCliente() {
////        return nomeCliente;
////    }
////
////    public void setNomeCliente(String nomeCliente) {
////        this.nomeCliente = nomeCliente;
////    }
////
////    public String getNomePet() {
////        return nomePet;
////    }
////
////    public void setNomePet(String nomePet) {
////        this.nomePet = nomePet;
////    }
//
//
