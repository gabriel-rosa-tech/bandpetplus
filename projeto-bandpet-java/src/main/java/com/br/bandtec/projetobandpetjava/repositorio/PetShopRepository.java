package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.PetShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetShopRepository extends JpaRepository<PetShop, Integer> {

    PetShop findByEmailAndSenha(String email,String senha);
    PetShop findByAssinaturaEletronicaAndIdPetshop(String assinaturaEletronica, Integer idPetshop);
}
