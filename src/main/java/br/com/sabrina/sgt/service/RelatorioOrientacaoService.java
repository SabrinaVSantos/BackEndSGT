package br.com.sabrina.sgt.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sabrina.sgt.dao.RelatorioOrientacaoRepository;
import br.com.sabrina.sgt.dao.TCCRepository;
import br.com.sabrina.sgt.entidade.RelatorioOrientacao;

@Service
public class RelatorioOrientacaoService {
	
	@Autowired
	protected RelatorioOrientacaoRepository relatorioOrientacaoRepository;

	@Autowired
	protected TCCRepository tccRepository;
	
	public List<RelatorioOrientacao> listarPorIdTcc(Long idTcc) {
		return relatorioOrientacaoRepository.listarPorIdTcc(idTcc);
	}
	
	public RelatorioOrientacao recuperar(Long id) {
		return relatorioOrientacaoRepository.findById(id).get();
	}

	public RelatorioOrientacao salvar(RelatorioOrientacao relatorio) {
		try {
			relatorio.setData(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(relatorio.getDataFormatada()).getTime()));
		} catch (ParseException e) {
			//log erro
			e.printStackTrace();
		}
		return relatorioOrientacaoRepository.save(relatorio);
	}

	public boolean apagar(Long id) {
		relatorioOrientacaoRepository.deleteById(id);
		return true;
	}

}
