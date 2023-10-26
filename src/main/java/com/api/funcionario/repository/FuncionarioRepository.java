package com.api.funcionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.funcionario.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findAllByOrderByIdAsc();
	List<Funcionario> findAllByOrderByNomeAsc();

}
