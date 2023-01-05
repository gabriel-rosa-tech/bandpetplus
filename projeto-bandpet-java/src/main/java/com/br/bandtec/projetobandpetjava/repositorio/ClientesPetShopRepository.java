package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.ClientesPetShop;
import com.br.bandtec.projetobandpetjava.dominio.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientesPetShopRepository extends JpaRepository<ClientesPetShop, Integer> {

    List<ClientesPetShop> findByFkPetshopIdPetshop(Integer IdPetshop);
    Optional<ClientesPetShop> findByNomeCliente(String nome);
}
