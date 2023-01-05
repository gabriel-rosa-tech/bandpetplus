package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

    @Query(value = "SELECT * from ordem_servico as ordem \n" +
            "\tJOIN animal_cliente as a on  a.id_animal = ordem.fk_animal_cliente_id_animal \n" +
            "\tJOIN clientes_pet_shop as cli on a.fk_cliente_id_cliente = cli.id_cliente \n" +
            "\tJOIN pet_shop as pet on cli.fk_petshop_id_petshop = pet.id_petshop \n" +
            "\tWhere pet.id_petshop= ?1 AND hora_entrada BETWEEN ?2 and ?3 ;", nativeQuery = true)
    List<OrdemServico> finByAllOrdem(Integer id, String inicio, String fim);

    List<OrdemServico> findByFkAnimalClienteIdAnimal(Integer FkAnimalCliente);

    @Query(value = "SELECT * FROM ordem_servico as o \n" +
            "\t\t\t JOIN animal_cliente as a ON o.fk_animal_cliente_id_animal = a.id_animal \n" +
            "\t\t\t JOIN clientes_pet_shop  as cp ON a.fk_cliente_id_cliente = cp.id_cliente \n" +
            "\t\t\t JOIN pet_shop as p ON cp.fk_petshop_id_petshop = id_petshop \n" +
            "\t\t\t WHERE p.id_petshop = ?1 AND a.nome_animal LIKE ?2%", nativeQuery = true)
    List<OrdemServico> findByNome(int id,String nome);

    @Query(value = "SELECT TOP(1)* FROM ordem_servico as ordem \n" +
            "\tJOIN animal_cliente as animal ON ordem.fk_animal_cliente_id_animal = animal.id_animal\n" +
            "\tJOIN clientes_pet_shop as cliente ON animal.fk_cliente_id_cliente = cliente.id_cliente\n" +
            "\tJOIN pet_shop as pet ON cliente.fk_petshop_id_petshop = pet.id_petshop \n" +
            "\tWHERE ordem.fk_animal_cliente_id_animal = ?1 ORDER BY id_ordem_servico DESC\n ",
            nativeQuery = true)
    OrdemServico findByIdAnimal(Integer idAnimal);

    @Query(value = "SELECT * from ordem_servico as ordem\n" +
            "            JOIN animal_cliente as a on  a.id_animal = ordem.fk_animal_cliente_id_animal\n" +
            "            JOIN clientes_pet_shop as cli on a.fk_cliente_id_cliente = cli.id_cliente \n" +
            "            JOIN pet_shop as pet on cli.fk_petshop_id_petshop = pet.id_petshop\n" +
            "            Where pet.id_petshop=?1\n", nativeQuery = true)
    List<OrdemServico> findByIdPetshop(int idPetshop);
}
