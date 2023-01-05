package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.AnimalCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalClienteRepository extends JpaRepository<AnimalCliente, Integer> {

    List<AnimalCliente> findByFkClienteIdCliente(int fkCliente);

    @Query(value = "SELECT * from animal_cliente as animal\n" +
            "            JOIN clientes_pet_shop as cli on animal.fk_cliente_id_cliente = cli.id_cliente \n" +
            "            JOIN pet_shop as pet on cli.fk_petshop_id_petshop = pet.id_petshop \n" +
            "            Where pet.id_petshop= ?1 \n", nativeQuery = true)
    List<AnimalCliente> findByIdPetshop(int idPetshop);
//    List<AnimalCliente> findByFkClienteIdClienteAndNomeAnimal(int fkCliente, String nomeAnimal);

    @Query(value = "SELECT * from animal_cliente as ac \n" +
            "\t\t\t JOIN clientes_pet_shop as cp on ac.fk_cliente_id_cliente = cp.id_cliente \n" +
            "\t\t\t JOIN pet_shop AS p on cp.fk_petshop_id_petshop = p.id_petshop \n" +
            "       Where p.id_petshop= ?1 AND ac.nome_animal LIKE ?2%",nativeQuery = true)
    List<AnimalCliente> findByNome(int id, String nomeAnimal);

}
