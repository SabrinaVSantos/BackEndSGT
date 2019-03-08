package br.com.sabrina.sgt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sabrina.sgt.dao.AlunoRepository;
import br.com.sabrina.sgt.dao.DocumentoRepository;
import br.com.sabrina.sgt.dao.PreProjetoRepository;
import br.com.sabrina.sgt.dao.ProfessorRepository;
import br.com.sabrina.sgt.dto.CampoEnum;
import br.com.sabrina.sgt.entidade.Documento;
import br.com.sabrina.sgt.entidade.EstadoAvaliacaoEnum;
import br.com.sabrina.sgt.entidade.PreProjeto;

@Service
public class PreProjetosService {
	@Autowired
	protected PreProjetoRepository preProjetoRepository;
	@Autowired
	protected AlunoRepository alunoRepository;
	@Autowired
	protected ProfessorRepository professorRepository;
	@Autowired
	protected DocumentoRepository documentoRepository;

	public List<PreProjeto> pesquisar(String termo, CampoEnum campo) {
		termo = "%" + termo.toUpperCase() + "%";
		List<PreProjeto> resultado = new ArrayList<PreProjeto>();
		switch(campo) {
		case ALUNOS:
			resultado = preProjetoRepository.findByAlunos(termo);
			break;
		case ORIENTADORES:
			resultado = preProjetoRepository.findByOrientadores(termo);
			break;
		case TEMA:
			resultado = preProjetoRepository.findByTemaIgnoreCaseContainingOrderByTema(termo);
			break;
		default: 
			break;
		}
		return resultado;
	}

	public PreProjeto salvar(PreProjeto preProjeto) {
		try {
			preProjeto.setAluno1(alunoRepository.findById(preProjeto.getIdAluno1()).get());
			if(preProjeto.getIdAluno2() != null) {
				preProjeto.setAluno2(alunoRepository.findById(preProjeto.getIdAluno2()).get());
			}
			if(preProjeto.getIdAvaliador1() != null) {
				preProjeto.setAvaliador1(professorRepository.findById(preProjeto.getIdAvaliador1()).get());
			}
			if(preProjeto.getIdAvaliador2() != null) {
				preProjeto.setAvaliador2(professorRepository.findById(preProjeto.getIdAvaliador2()).get());
			}
			if(preProjeto.getIdOrientador() != null) {
				preProjeto.setOrientador(professorRepository.findById(preProjeto.getIdOrientador()).get());
			}
			if(preProjeto.getIdCoOrientador() != null) {
				preProjeto.setCoOrientador(professorRepository.findById(preProjeto.getIdCoOrientador()).get());
			}
			if(preProjeto.getId() == null) {
				preProjeto.setEstadoAvaliacao(EstadoAvaliacaoEnum.CADASTRADO);
			}
			return preProjetoRepository.save(preProjeto);
		} catch(DataIntegrityViolationException e) {
			throw new RuntimeException("Já existe um PreProjeto cadastrado com os dados informados na requisição.");
		}
	}

	public boolean apagar(Long id) {
		preProjetoRepository.deleteById(id);
		return true;
	}

	public PreProjeto recuperarPorId(Long id) {
		PreProjeto preProjeto = preProjetoRepository.findById(id).get();
		atualizaDocumentosPreProjeto(preProjeto);
		return preProjeto;
	}

	public PreProjeto recuperarPorIdAluno(Long idAluno) {
		PreProjeto preProjeto = preProjetoRepository.findByIdAluno(idAluno);
		atualizaDocumentosPreProjeto(preProjeto);
		return preProjeto;
	}
	
	public List<PreProjeto> listarTodos() {
		return preProjetoRepository.findByOrderByTema();
	}

	private void atualizaDocumentosPreProjeto(PreProjeto preProjeto) {
		preProjeto.setArquivoPreProjeto(preProjeto.getAnexo());
		List<Documento> documentos = documentoRepository.listarPorIdAluno(preProjeto.getAluno1().getId());
		for (Documento documento : documentos) {
			switch(documento.getTipo()) {
			case FICHA_INSCRICAO:
				preProjeto.setArquivoFichaInscricao(documento.getAnexo());
				break;
			case HISTORICO_ESCOLAR:
				preProjeto.setArquivoHistorico(documento.getAnexo());
				break;
			case CARTA_DE_ACEITE:
				preProjeto.setArquivoCartaAceite(documento.getAnexo());
				break;
			default:
				break;
			}
		}
	}

}
