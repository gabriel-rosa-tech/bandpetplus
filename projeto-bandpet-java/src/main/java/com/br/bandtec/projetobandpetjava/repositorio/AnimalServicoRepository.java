package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.AnimaisServicos;
import com.br.bandtec.projetobandpetjava.dominio.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimalServicoRepository extends JpaRepository<AnimaisServicos, Integer> {

    List<AnimaisServicos> findByFkOrdemServico(Integer fkOrdemServico);

    List<AnimaisServicos> findByFkServico(Integer fkServico);

    List<AnimaisServicos> findByFkOrdemServicoIdOrdemServico(Integer idOrdemServico);

    @Query(value = "DELETE FROM animais_servicos WHERE fk_ordem_servico_id_ordem_servico = ?1",
        nativeQuery = true)
    long deleteFkOrdemServicoIdOrdemServico(Integer fkOrdemServico);
}
