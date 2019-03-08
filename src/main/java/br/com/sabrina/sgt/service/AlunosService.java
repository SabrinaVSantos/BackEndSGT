package br.com.sabrina.sgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sabrina.sgt.dao.AlunoRepository;
import br.com.sabrina.sgt.entidade.Aluno;

@Service
public class AlunosService {
	
	@Autowired
	protected AlunoRepository alunoRepository;

	public List<Aluno> pesquisar(String nome) {
		return alunoRepository.findByNomeIgnoreCaseContaining(nome);
	}

	public List<Aluno> listarTodos() {
		return alunoRepository.findByOrderByNome();
	}

	public Aluno recuperarPorMatriculaESenha(Long matricula, String senha) {
		return alunoRepository.findByMatriculaAndSenha(matricula, senha);
	}
	
	public Aluno recuperarPorMatricula(Long matricula) {
		return alunoRepository.findByMatricula(matricula);
	}

	public Aluno recuperarPorId(Long id) {
		return alunoRepository.findById(id).get();
	}
	
	public Aluno salvar(Aluno aluno) {
		try {
			return alunoRepository.save(aluno);
		} catch(DataIntegrityViolationException e) {
			throw new RuntimeException("Já existe um aluno cadastrado com os dados informados na requisição.");
		}
	}

	public boolean apagar(Long id) {
		 alunoRepository.deleteById(id);
		 return true;
	}
	

	
}
