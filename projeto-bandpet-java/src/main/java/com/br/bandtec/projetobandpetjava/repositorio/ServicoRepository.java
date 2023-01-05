package com.br.bandtec.projetobandpetjava.repositorio;


import com.br.bandtec.projetobandpetjava.dominio.Servico;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository  extends JpaRepository<Servico, Integer> {

}
