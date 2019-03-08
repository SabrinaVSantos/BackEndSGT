package br.com.sabrina.sgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sabrina.sgt.dao.InstituicaoRepository;
import br.com.sabrina.sgt.entidade.Instituicao;

@Service
public class InstituicoesService {
	
	@Autowired
	protected InstituicaoRepository instituicaoRepository;

	public List<Instituicao> listarTodos() {
		return instituicaoRepository.findByOrderByNome();
	}

	public Instituicao recuperarPorId(Long id) {
		return instituicaoRepository.findById(id).get();
	}
	
	public Instituicao salvar(Instituicao instituicao) {
		try {
			return instituicaoRepository.save(instituicao);
		} catch(DataIntegrityViolationException e) {
			throw new RuntimeException("Já existe uma instiuição cadastrada com os dados informados na requisição.");
		}
	}

	public List<Instituicao> pesquisar(String nome) {
		return instituicaoRepository.findByNomeIgnoreCaseContaining(nome);
	}

	public boolean apagar(Long id) {
		instituicaoRepository.deleteById(id);
		 return true;
	}
	

	
}
