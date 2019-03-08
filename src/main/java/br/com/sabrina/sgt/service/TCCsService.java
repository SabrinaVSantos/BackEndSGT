package br.com.sabrina.sgt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sabrina.sgt.dao.AlunoRepository;
import br.com.sabrina.sgt.dao.ProfessorRepository;
import br.com.sabrina.sgt.dao.RelatorioOrientacaoRepository;
import br.com.sabrina.sgt.dao.TCCRepository;
import br.com.sabrina.sgt.dto.CampoEnum;
import br.com.sabrina.sgt.entidade.RelatorioOrientacao;
import br.com.sabrina.sgt.entidade.TCC;

@Service
public class TCCsService {
	@Autowired
	protected TCCRepository tccRepository;
	@Autowired
	protected AlunoRepository alunoRepository;
	@Autowired
	protected ProfessorRepository professorRepository;
	@Autowired
	protected RelatorioOrientacaoRepository relatorioOrientacaoRepository;

	public List<TCC> pesquisar(String termo, CampoEnum campo) {
		termo = "%" + termo.toUpperCase() + "%";
		List<TCC> resultado = new ArrayList<TCC>();
		switch(campo) {
		case ALUNOS:
			resultado = tccRepository.findByAlunos(termo);
			break;
		case ORIENTADORES:
			resultado = tccRepository.findByOrientadores(termo);
			break;
		case TEMA:
			resultado = tccRepository.findByTemaIgnoreCaseContainingOrderByTema(termo);
			break;
		default: 
			break;
		}
		return resultado;
	}

	public TCC salvar(TCC tcc) {
		try {
			if(tcc.getId() != null) {
				tcc.setOrientacoes(new HashSet<RelatorioOrientacao>(relatorioOrientacaoRepository.listarPorIdTcc(tcc.getId())));
			}
			tcc.setAluno1(alunoRepository.findById(tcc.getIdAluno1()).get());
			if(tcc.getIdAluno2() != null) {
				tcc.setAluno2(alunoRepository.findById(tcc.getIdAluno2()).get());
			}
			if(tcc.getIdOrientador() != null) {
				tcc.setOrientador(professorRepository.findById(tcc.getIdOrientador()).get());
			}
			if(tcc.getIdCoOrientador() != null) {
				tcc.setCoOrientador(professorRepository.findById(tcc.getIdCoOrientador()).get());
			}
			if(tcc.getIdMembroBanca1() != null) {
				tcc.setMembroBanca1(professorRepository.findById(tcc.getIdMembroBanca1()).get());
			}
			if(tcc.getIdMembroBanca2() != null) {
				tcc.setMembroBanca2(professorRepository.findById(tcc.getIdMembroBanca2()).get());
			}
			return tccRepository.save(tcc);
		} catch(DataIntegrityViolationException e) {
			throw new RuntimeException("Já existe um TCC cadastrado com os dados informados na requisição.");
		}
	}

	public boolean apagar(Long id) {
		tccRepository.deleteById(id);
		return true;
	}

	public TCC recuperarPorId(Long id) {
		return tccRepository.findById(id).get();
	}
	
	public List<TCC> listarTodos() {
		return tccRepository.findByOrderByTema();
	}

}
