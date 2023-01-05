package com.br.bandtec.projetobandpetjava.controle;


import com.br.bandtec.projetobandpetjava.dominio.*;
import com.br.bandtec.projetobandpetjava.repositorio.AnimalClienteRepository;
import com.br.bandtec.projetobandpetjava.repositorio.ClientesPetShopRepository;
import com.br.bandtec.projetobandpetjava.repositorio.OrdemServicoRepository;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/ordem")
public class OrdemServicoController {

    @Autowired
    OrdemServicoRepository repository;

    @Autowired
    AnimalClienteRepository repositoryA;

    @Autowired
    PetShopRepository repositoryPetShop;

    @Autowired
    ClientesPetShopRepository repositoryCliente;
    ChamadaSlack slack = new ChamadaSlack();


    @PostMapping
    public ResponseEntity postOrdemServico(@RequestBody OrdemServico novoOrdemServico)
            throws IOException, InterruptedException, Exception{

        if (repositoryA.existsById(novoOrdemServico.getFkAnimalCliente().getIdAnimal())) {
            Optional<AnimalCliente> animalCliente = repositoryA.findById(novoOrdemServico.getFkAnimalCliente().getIdAnimal());
            System.out.println("idAnimal"+animalCliente.get().getIdAnimal());
            System.out.println("Cliente" + animalCliente.get().getFkCliente().getIdCliente());

            ClientesPetShop cliente = animalCliente.get().getFkCliente();
            System.out.println("IdCliente"+cliente.getIdCliente());

            PetShop petShop = cliente.getFkPetshop();
            System.out.println("id"+petShop.getIdPetshop());

            if (petShop.getCartaoLigado()==1){
                int cartao = cliente.getServicoUsado();
                if (cartao != petShop.getLimiteFidelidade()){
                    //Incrementa mais um
                    cartao++;
                    cliente.setServicoUsado(cartao);

                } else if (cartao== petShop.getLimiteFidelidade()){
                    //envia disparo para o Slack e reseta para zero
                    cartao = 0;
                    cliente.setServicoUsado(cartao);
                    slack.enviarMsnCartao(cliente.getNomeCliente());
                }
                else {
                    System.out.println("Não foi possível realizar nenhuma dessas operações");
                }
            }
            repository.save(novoOrdemServico);
            repositoryCliente.save(cliente);
            return ResponseEntity.status(201).build();
        } else{
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping
    public ResponseEntity getOrdemServico(){
        List<OrdemServico> ordemServico = repository.findAll();

        if(ordemServico.isEmpty()){
            return status(204).build();
        }
        else{
            return status(200).body(ordemServico);
        }
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity getOrdemPorIdAnimal(@PathVariable int id){

        if (repositoryA.existsById(id)){
            OrdemServico ordem = repository.findByIdAnimal(id);
            return ResponseEntity.status(200).body(ordem);
        } else{
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrdemPorIdOrdem(@PathVariable Integer id){

        if(repository.existsById(id)) {
            return ResponseEntity.status(200).body(repository.findById(id));
        }
        else{
            return ResponseEntity.status(400).build();
        }
    }

    //Buscar ordem pelo nome do animal
    @GetMapping("/{id}/nomeAnimal")
    public ResponseEntity getOrdemByNome(@PathVariable int id,
                                         @RequestParam String nome){
        List<OrdemServico> lista = repository.findByNome(id,nome);
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(lista);
        }
    }

    //Busca pelo horario fornecido
    @GetMapping("/{id}/dia")
    public ResponseEntity getOrdemByDia(@PathVariable int id,
                                        @RequestParam String inicio,
                                        @RequestParam String fim){
        List<OrdemServico> list = repository.finByAllOrdem(id,inicio,fim);

        if (list.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(list);
        }
    }

    //Gera a lista em React
    @GetMapping("/petshop/{id}")
    public ResponseEntity getOrdemPorIdPetshop(@PathVariable int id){
        List<OrdemServico> ordem;

        if (repositoryPetShop.existsById(id)){
            ordem = repository.findByIdPetshop(id);

            if (ordem.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else{
                return status(200).body(ordem);
            }
        } else {
            return status(404).build();
        }
    }

    @PutMapping("/{id}/{finalizado}")
    public ResponseEntity putOrdemServico(@RequestBody OrdemServico ordemServicoNovo,
                                   @PathVariable int id,
                                    @PathVariable int finalizado)throws IOException, InterruptedException, Exception{

        if (repository.existsById(id)){
            ordemServicoNovo.setIdOrdemServico(id);
            if (ordemServicoNovo.getHoraSaida() != ordemServicoNovo.getHoraEntrada() && finalizado==0){
                System.out.println("Ordem saída: "+ordemServicoNovo.getHoraSaida());
                System.out.println("Ordem entrada: "+ordemServicoNovo.getHoraEntrada());
                slack.enviarMensagemPronto();
            }
            repository.save(ordemServicoNovo);
            return status(200).build();
        }
        else {
            return status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrdemServico(@PathVariable int id){

        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        else{
            return status(404).build();
        }
    }
}
