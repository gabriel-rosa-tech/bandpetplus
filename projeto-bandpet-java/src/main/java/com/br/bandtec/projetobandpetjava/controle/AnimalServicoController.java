package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.AnimaisServicos;
import com.br.bandtec.projetobandpetjava.dominio.Servico;
import com.br.bandtec.projetobandpetjava.repositorio.AnimalServicoRepository;
import com.br.bandtec.projetobandpetjava.repositorio.OrdemServicoRepository;
import com.br.bandtec.projetobandpetjava.repositorio.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/animal-servico")
public class AnimalServicoController {

    @Autowired
    AnimalServicoRepository repository;

    @Autowired
    OrdemServicoRepository repositoryO;

    @Autowired
    ServicoRepository repositoryS;

    @PostMapping
    public ResponseEntity postAnimalServico(@RequestBody AnimaisServicos novoAnimaisServicos){

        if (repositoryO.existsById(novoAnimaisServicos.getFkOrdemServico().getIdOrdemServico()) &&
                repositoryS.existsById(novoAnimaisServicos.getFkServico().getIdServico())) {
            repository.save(novoAnimaisServicos);
            return ResponseEntity.status(201).build();
        } else{
            return ResponseEntity.status(400).build();
        }
    }


    @GetMapping
    public ResponseEntity getAnimalServico(){
        List<AnimaisServicos> animaisServicos = repository.findAll();

        if(animaisServicos.isEmpty()){
            return status(204).build();
        }
        else{
            return ok(animaisServicos);
        }
    }

    @GetMapping("/ordem/{id}")
    public ResponseEntity getServicosByIdOrdem(@PathVariable Integer id){
        List<AnimaisServicos> servicoAnimal = repository.findByFkOrdemServicoIdOrdemServico(id);
        if(servicoAnimal.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(servicoAnimal);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putOrdemServico(@RequestBody AnimaisServicos animaisServicosNovo,
                                          @PathVariable int id){
        if(repository.existsById(id)){
            animaisServicosNovo.setIdAnimalServico(id);
            repository.save(animaisServicosNovo);
            return status(200).build();
        }
        else {
            return status(404).build();
        }
    }

    @DeleteMapping("/ordem/{id}")
    public ResponseEntity deleteAnimalServico(@PathVariable Integer id){

        if(repositoryO.existsById(id)){
            System.out.println("Entrou");
            long registro = repository.deleteFkOrdemServicoIdOrdemServico(id);
            return ResponseEntity.status(200).build();
        }
        else{
            return status(404).build();
        }
    }


}
