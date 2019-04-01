package br.com.sabrina.sgt.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.sabrina.sgt.entidade.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	
	//recuperando documentos de um aluno
	@Query("SELECT a FROM Documento a WHERE a.aluno.id = :idAluno")
    List<Documento> listarPorIdAluno(@Param("idAluno") Long idAluno);

	@Transactional
	@Modifying
	@Query("DELETE FROM Documento a WHERE a.aluno.id = :idAluno")
	void deleteByIdAluno(@Param("idAluno") Long idAluno);
}
