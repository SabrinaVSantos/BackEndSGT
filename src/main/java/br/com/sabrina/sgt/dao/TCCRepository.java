package br.com.sabrina.sgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.sabrina.sgt.entidade.TCC;

public interface TCCRepository extends CrudRepository<TCC, Long> {
	List<TCC> findByOrderByTema();

	List<TCC> findByTemaIgnoreCaseContainingOrderByTema(String tema);

	@Query("SELECT a FROM TCC a LEFT JOIN a.aluno2 b WHERE UPPER(a.aluno1.nome) like :nome OR UPPER(b.nome) like :nome order by a.tema")
	List<TCC> findByAlunos(String nome);

	@Query("SELECT a FROM TCC a LEFT JOIN a.coOrientador b WHERE UPPER(a.orientador.nome) like :nome OR UPPER(b.nome) like :nome order by a.tema")
	List<TCC> findByOrientadores(String nome);
}
