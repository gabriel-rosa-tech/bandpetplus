package com.br.bandtec.projetobandpetjava.controle;

import ch.qos.logback.core.net.server.Client;
import com.br.bandtec.projetobandpetjava.dominio.ClientesPetShop;
import com.br.bandtec.projetobandpetjava.dominio.Fornecedor;
import com.br.bandtec.projetobandpetjava.dominio.ListaObj;
import com.br.bandtec.projetobandpetjava.dominio.PetShop;
import com.br.bandtec.projetobandpetjava.repositorio.ClientesPetShopRepository;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.LocalGregorianCalendar;

import javax.swing.text.html.Option;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/clientes")
public class ClientesPetShopController {

    @Autowired
    ClientesPetShopRepository repository;

    @Autowired
    PetShopRepository repositoryP;

    @GetMapping
    public ResponseEntity getClientes(){
        List<ClientesPetShop> clientes = repository.findAll();

        if(clientes.isEmpty()){
            return status(204).build();
        }
        else{
            return status(200).body(clientes);
        }
    }

    @GetMapping("/buscarnome")
    public ResponseEntity getClientePorNome(@RequestParam String nome){
        System.out.println(nome);
        Optional<ClientesPetShop> cliente = repository.findByNomeCliente(nome);

        if (cliente.isPresent()){
            return ResponseEntity.status(200).body(cliente);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<ClientesPetShop> cliente = repository.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.status(200).body(cliente);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/petshop/{id}")
    public ResponseEntity getIDPorPetshop(@PathVariable int id){
       List<ClientesPetShop> cliente = repository.findByFkPetshopIdPetshop(id);

        if (cliente.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(cliente);
        }
    }

    @PostMapping
    public ResponseEntity postClientesPetShop(@RequestBody ClientesPetShop novoCliente){
        if (repositoryP.existsById(novoCliente.getFkPetshop().getIdPetshop())) {
            repository.save(novoCliente);
            return ResponseEntity.status(201).build();
        } else{
            return ResponseEntity.status(400).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity putCliente(@RequestBody ClientesPetShop clienteNovo,
                                     @PathVariable int id){
        if(repository.existsById(id)){
            clienteNovo.setIdCliente(id);
            repository.save(clienteNovo);
            return status(200).build();
        }
        else {
            return status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCliente(@PathVariable int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        else{
            return status(404).build();
        }
    }
}
