package br.com.sabrina.sgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.sabrina.sgt.entidade.PreProjeto;

public interface PreProjetoRepository extends CrudRepository<PreProjeto, Long> {

	@Query("SELECT a FROM PreProjeto a WHERE a.aluno1.id = :idAluno")
    PreProjeto findByIdAluno(@Param("idAluno") Long idAluno);

	List<PreProjeto> findByOrderByTema();

	List<PreProjeto> findByTemaIgnoreCaseContainingOrderByTema(String tema);

	@Query("SELECT a FROM PreProjeto a LEFT JOIN a.aluno2 b WHERE UPPER(a.aluno1.nome) like :nome OR UPPER(b.nome) like :nome order by a.tema")
	List<PreProjeto> findByAlunos(String nome);

	@Query("SELECT a FROM PreProjeto a LEFT JOIN a.coOrientador b WHERE UPPER(a.orientador.nome) like :nome OR UPPER(b.nome) like :nome order by a.tema")
	List<PreProjeto> findByOrientadores(String nome);
}
