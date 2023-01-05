package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.Servico;
import com.br.bandtec.projetobandpetjava.repositorio.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    @Autowired
    ServicoRepository repository;

    @PostMapping
    public ResponseEntity postServico(@RequestBody Servico novoServico){
        repository.save(novoServico);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getServico(){
        List<Servico> servicos = repository.findAll();

        if(servicos.isEmpty()){
            return status(204).build();
        }
        else{
            return status(200).body(servicos);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity putServico(@RequestBody Servico novoServico,
                                        @PathVariable int id){

        if(repository.existsById(id)){
            novoServico.setIdServico(id);
            repository.save(novoServico);
            return status(200).build();
        }
        else {
            return status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServico(@PathVariable int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        else{
            return status(404).build();
        }
    }
}
