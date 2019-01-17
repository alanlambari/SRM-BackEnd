package com.srm.formulario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srm.formulario.domain.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Integer> {

}
