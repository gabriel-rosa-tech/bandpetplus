package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.Fornecedor;
import com.br.bandtec.projetobandpetjava.dominio.PetShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    List<Fornecedor> findByFkPetshopIdPetshop(Integer idPetShop);
    List<Fornecedor> findByIdFornecedor(Integer idFornecedor);
    // where fk_petshop_id_petshop

    @Query(value = "SELECT * from fornecedor as f \n" +
            "\t\t\t JOIN pet_shop as p on f.fk_petshop_id_petshop = p.id_petshop \n" +
            "Where p.id_petshop= ?1 AND f.empresa LIKE ?2%", nativeQuery = true)
    List<Fornecedor> findByNome(Integer id,String nomeEmpresa);
}
