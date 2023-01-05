package com.br.bandtec.projetobandpetjava.repositorio;

import com.br.bandtec.projetobandpetjava.dominio.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnexoRepository extends JpaRepository<Anexo, Integer> {
}