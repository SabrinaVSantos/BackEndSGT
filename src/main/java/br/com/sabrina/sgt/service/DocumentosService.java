package br.com.sabrina.sgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sabrina.sgt.dao.DocumentoRepository;
import br.com.sabrina.sgt.entidade.Documento;

@Service
public class DocumentosService {
	
	@Autowired
	protected DocumentoRepository documentoRepository;
	
	public List<Documento> listarPorIdAluno(Long idAluno) {
		return documentoRepository.listarPorIdAluno(idAluno);
	}
	
	public Documento recuperar(Long idDocumento) {
		return documentoRepository.findById(idDocumento).get();
	}
	
	public Documento salvar(Documento documento) {
		return documentoRepository.save(documento);
	}
	 
	public boolean apagar(Documento documento) {
		documentoRepository.deleteById(documento.getId());
		return true;
	}

	public boolean apagarTodosDocumentos(Long idAluno) {
		documentoRepository.deleteByIdAluno(idAluno);
		return true;
	}
}
