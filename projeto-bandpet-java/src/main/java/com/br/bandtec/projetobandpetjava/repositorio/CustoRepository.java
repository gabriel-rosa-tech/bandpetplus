package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.Custo;
import com.br.bandtec.projetobandpetjava.dominio.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustoRepository extends JpaRepository<Custo, Integer> {

    List<Custo> findByFkPetshopIdPetshop(Integer fkPetshop);


    List<Custo> findByFkPetshopIdPetshopAndDataConsumoBetween(Integer fkPetshop, String inicio,
                                                              String fim);
}
