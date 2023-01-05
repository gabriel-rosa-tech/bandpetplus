package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.AnimalCliente;
import com.br.bandtec.projetobandpetjava.repositorio.AnimalClienteRepository;
import com.br.bandtec.projetobandpetjava.repositorio.ClientesPetShopRepository;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/animal")
public class AnimalClienteController {

    @Autowired
    AnimalClienteRepository repository;

    @Autowired
    ClientesPetShopRepository repositoryC;

    @Autowired
    PetShopRepository repositoryPetShop;

    @PostMapping
    public ResponseEntity postAnimalCliente(@RequestBody AnimalCliente novoAnimalC){
        if (repositoryC.existsById(novoAnimalC.getFkCliente().getIdCliente())) {
            repository.save(novoAnimalC);
            return ResponseEntity.status(201).build();

        } else{
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping
    public ResponseEntity getAnimal(){
        List<AnimalCliente> animais = repository.findAll();

        if(animais.isEmpty()){
            return status(204).build();
        }
        else{
            return status(200).body(animais);
        }
    }

    //Seleciona os animais pelo ID DO CLIENTE/ Perfil
    @GetMapping("/cliente/{id}")
    public ResponseEntity getAnimalPorIdCliente(@PathVariable int id){
        List<AnimalCliente> animais;

        if (repositoryC.existsById(id)){
            animais = repository.findByFkClienteIdCliente(id);

            if (animais.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return status(200).body(animais);
            }
        } else {
            return status(404).build();
        }
    }

    //Seleciona os clientes pelo ID do Petshop
    @GetMapping("/petshop/{id}")
    public ResponseEntity getAnimalPorIdPetshop(@PathVariable int id){
        List<AnimalCliente> animais ;

        if (repositoryPetShop.existsById(id)){
            animais = repository.findByIdPetshop(id);

            if (animais.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return status(200).body(animais);
            }
        } else {
            return status(404).build();
        }
    }

    @GetMapping("/{id}/nome")
    public ResponseEntity getAnimalByNome(@PathVariable int id,
                                          @RequestParam String nome){

        List<AnimalCliente> lista = repository.findByNome(id, nome);
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(lista);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAnimalById(@PathVariable Integer id){
        Optional<AnimalCliente> animal = repository.findById(id);

        if (animal.isPresent()){
            return ResponseEntity.status(200).body(animal);
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity putAnimal(@RequestBody AnimalCliente animalNovo,
                                     @PathVariable int id){

        if(repository.existsById(id)){
            animalNovo.setIdAnimal(id);
            repository.save(animalNovo);
            return status(200).build();
        }
        else {
            return status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnimal(@PathVariable int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        else{
            return status(404).build();
        }
    }
}
