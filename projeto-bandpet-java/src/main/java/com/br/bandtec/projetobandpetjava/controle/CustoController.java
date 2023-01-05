package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.Custo;
import com.br.bandtec.projetobandpetjava.dominio.Fornecedor;
import com.br.bandtec.projetobandpetjava.dominio.PetShop;
import com.br.bandtec.projetobandpetjava.repositorio.CustoRepository;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/custo")
public class CustoController {
    @Autowired
    CustoRepository repository;
    @Autowired
    PetShopRepository repositoryP;
    @PostMapping
    public ResponseEntity postCusto(@RequestBody Custo novoCusto){
        if (repositoryP.existsById(novoCusto.getFkPetshop().getIdPetshop())) {
            repository.save(novoCusto);
            return ResponseEntity.status(201).build();
        } else{
            return ResponseEntity.status(400).build();
        }
    }
    @GetMapping
    public ResponseEntity getCusto(){
        List<Custo> custos = repository.findAll();

        if(custos.isEmpty()){
            return status(204).build();
        }
        else{
            return status(200).body(custos);
        }
    }
    @GetMapping("/petshop/{id}")
    public ResponseEntity getCustoPorPetShop(@PathVariable Integer id) {

        if(repositoryP.existsById(id)){
            List<Custo> list = repository.findByFkPetshopIdPetshop(id);
            if(list.isEmpty()){
                System.out.println(list);
                return ResponseEntity.status(204).body(list);
            }else{
                return ResponseEntity.status(200).body(list);
            }
        }
        return ResponseEntity.status(400).build();
    }
    @GetMapping("{id}")
    public ResponseEntity getCusto(@PathVariable Integer id){
        Optional<Custo> custo = repository.findById(id);
        if(custo.isPresent()){
            return ResponseEntity.status(200).body(custo);
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putCusto(@RequestBody Custo custoNovo,
                                     @PathVariable int id){
        if(repository.existsById(id)){
            custoNovo.setIdCusto(id);
            repository.save(custoNovo);
            return status(200).build();
        }
        else {
            return status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCusto(@PathVariable int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        else{
            return status(404).build();
        }
    }
}
