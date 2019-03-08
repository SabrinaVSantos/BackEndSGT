package br.com.sabrina.sgt.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.sabrina.sgt.entidade.Aluno;

//Classe que faz o CRUD
//tem todos os métodos para acessar a tabela aluno no banco de dados:
//save, delete, findBy...
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	List<Aluno> findByOrderByNome();
	
	Aluno findByMatricula(Long matricula);

	List<Aluno> findByNomeIgnoreCaseContaining(String nome);

	Aluno findByMatriculaAndSenha(Long matricula, String senha);
}
