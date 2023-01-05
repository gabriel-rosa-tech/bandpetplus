package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.*;
import com.br.bandtec.projetobandpetjava.repositorio.FornecedorRepository;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorRepository repository;

    @Autowired
    PetShopRepository repositoryP;


    @PostMapping
    public ResponseEntity postFornecedor(@RequestBody Fornecedor novoFornecedor){
        if (repositoryP.existsById(novoFornecedor.getFkPetshop().getIdPetshop())) {
            repository.save(novoFornecedor);
            return ResponseEntity.status(201).build();
        }
        else {
            return ResponseEntity.status(400).build();
        }
    }


    @GetMapping
    public ResponseEntity getFornecedor(){
        List<Fornecedor> fornecedores = repository.findAll();

        if(fornecedores.isEmpty()){
            return status(204).build();
        }
        else{
            return status(200).body(fornecedores);
        }
    }

    @GetMapping("/{id}/nome")
    public ResponseEntity getFornecedoresByNome(@PathVariable Integer id,
                                                @RequestParam String nomeEmpresa){
        List<Fornecedor> lista = repository.findByNome(id,nomeEmpresa);

        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(lista);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getFornecedorPorIdFornecedor(@PathVariable Integer id){
        if(repository.existsById(id)) {
            return ResponseEntity.status(200).body(repository.findById(id));
        }
        else{
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/petshop/{id}")
    public ResponseEntity getFornecedorPorPetShop(@PathVariable Integer id) {

        if(repositoryP.existsById(id)){
            List<Fornecedor> list = repository.findByFkPetshopIdPetshop(id);
            if (list.isEmpty()){
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(list);
        }
        return ResponseEntity.status(404).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity putFornecedor(@RequestBody Fornecedor fornecedorNovo,
                                     @PathVariable int id){

        if(repository.existsById(id)){
            fornecedorNovo.setIdFornecedor(id);
            repository.save(fornecedorNovo);
            return status(200).build();
        }
        else {
            return status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFornecedor(@PathVariable int id){

        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        else{
            return status(404).build();
        }
    }
}
