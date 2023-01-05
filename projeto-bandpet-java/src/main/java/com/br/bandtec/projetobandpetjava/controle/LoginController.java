package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.PetShop;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private PetShopRepository repository;
    private Boolean sessao = false;

    @GetMapping("/autenticar")
    public ResponseEntity autenticarPetShop(@RequestParam String email, @RequestParam String senha){

        PetShop petshop = repository.findByEmailAndSenha(email,senha);
        if (petshop==null){
            return ResponseEntity.status(400).build();
        } else {
            if (sessao){
                return ResponseEntity.status(403).build();
            } else {
                sessao = true;
                return ResponseEntity.status(200).body(petshop);
            }
        }
    }

    @GetMapping("/autenticaradm")
    public ResponseEntity autenticarAdm(@RequestParam String assinaturaEletronica, @RequestParam Integer idPetshop){
        PetShop petshop = repository.findByAssinaturaEletronicaAndIdPetshop(assinaturaEletronica, idPetshop);

        if (petshop==null){
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(200).body(petshop);
        }
    }


    @GetMapping("/logoff")
    public ResponseEntity logOff(){
        if (sessao){
            sessao = false;
            return ResponseEntity.status(200).body("Sessão encerrada");
        } else {
            return ResponseEntity.status(401).body("A sessão ainda não foi iniciada");
        }
    }
}
