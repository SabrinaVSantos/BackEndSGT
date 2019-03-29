package br.com.sabrina.sgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.sabrina.sgt.entidade.PerfilUsuarioEnum;
import br.com.sabrina.sgt.entidade.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	Professor findBySiape(String siape);

	//recuperando professores seNDE
	@Query("SELECT a FROM Professor a WHERE a.nde = :seNDE")
    List<Professor> listarSeNDE(@Param("seNDE") Character seNDE);
	
	List<Professor> findByOrderByNome();

	List<Professor> findByNomeIgnoreCaseContaining(String nome);

	Professor findBySiapeAndSenha(String siape, String senha);

	Professor findByPerfil(PerfilUsuarioEnum perfil);
}
