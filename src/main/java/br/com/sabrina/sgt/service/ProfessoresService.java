package br.com.sabrina.sgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sabrina.sgt.dao.InstituicaoRepository;
import br.com.sabrina.sgt.dao.ProfessorRepository;
import br.com.sabrina.sgt.entidade.PerfilUsuarioEnum;
import br.com.sabrina.sgt.entidade.Professor;

@Service
public class ProfessoresService {
	@Autowired
	protected ProfessorRepository professorRepository;
	@Autowired
	protected InstituicaoRepository instituicaoRepository;

	public List<Professor> pesquisar(String nome) {
		return professorRepository.findByNomeIgnoreCaseContaining(nome);
	}

	public Professor salvar(Professor professor) {
		if(professor.getIdInstituicao() != null) {
			professor.setInstituicao(instituicaoRepository.findById(professor.getIdInstituicao()).get());
		}
		try {
			return professorRepository.save(professor);
		} catch(DataIntegrityViolationException e) {
			throw new RuntimeException("Já existe um professor cadastrado com os dados informados na requisição.");
		}
	}

	public boolean apagar(Long id) {
		professorRepository.deleteById(id);
		return true;
	}

	public Professor recuperarPorSiape(String siape) {
		return professorRepository.findBySiape(siape);
	}

	public Professor recuperarPorId(Long id) {
		return professorRepository.findById(id).get();
	}
	
	public List<Professor> listarTodos() {
		return professorRepository.findByOrderByNome();
	}

	public List<Professor> listarSeNDE() {
		return professorRepository.listarSeNDE('S');
	}

	public Professor recuperarPorSiapeESenha(String siape, String senha) {
		return professorRepository.findBySiapeAndSenha(siape, senha);
	}

	public Professor recuperarCoordenador() {
		return professorRepository.findByPerfil(PerfilUsuarioEnum.COORDENADOR);
	}

}
