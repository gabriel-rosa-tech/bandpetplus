package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.PetShop;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/petshop")
public class PetShopController {

    @Autowired
    private PetShopRepository repository;

    @PostMapping
    public ResponseEntity postPetShop(@RequestBody PetShop petShop){
        repository.save(petShop);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPetShop(@PathVariable int id){
        Optional<PetShop> petShop = repository.findById(id);
        if (petShop.isPresent()){
            return status(200).body(petShop);
        } else {
            return status(204).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putPetshop(@RequestBody PetShop petShopNovo,
                                     @PathVariable int id){

        if(repository.existsById(id)){
            petShopNovo.setIdPetshop(id);
            repository.save(petShopNovo);
            return status(200).body(petShopNovo);
        }
        else {
            
            return status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePetshop(@PathVariable int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        else{
            return status(404).build();
        }
    }
}
