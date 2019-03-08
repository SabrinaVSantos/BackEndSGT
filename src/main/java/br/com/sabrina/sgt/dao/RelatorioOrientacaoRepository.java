package br.com.sabrina.sgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.sabrina.sgt.entidade.RelatorioOrientacao;

public interface RelatorioOrientacaoRepository extends CrudRepository<RelatorioOrientacao, Long> {
	
	//recuperando relatorio de um tcc
	@Query("SELECT a FROM RelatorioOrientacao a WHERE a.idTcc = :idTcc order by a.id")
    List<RelatorioOrientacao> listarPorIdTcc(@Param("idTcc") Long idTcc);
}
	