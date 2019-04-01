package br.com.sabrina.sgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.sabrina.sgt.entidade.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

	List<Instituicao> findByNomeIgnoreCaseContaining(String nome);
	
	List<Instituicao> findByOrderByNome();
}
