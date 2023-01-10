package com.attornatus.projetopessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attornatus.projetopessoas.model.Endereco;
import com.attornatus.projetopessoas.model.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	public List<Endereco> findAllByPessoaId(@Param("id") Long id);

}
