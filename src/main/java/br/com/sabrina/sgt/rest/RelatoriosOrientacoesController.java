package br.com.sabrina.sgt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.entidade.RelatorioOrientacao;
import br.com.sabrina.sgt.service.RelatorioOrientacaoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/relatoriosorientacoes")
public class RelatoriosOrientacoesController {

	@Autowired
	protected RelatorioOrientacaoService relatorioOrientacaoService;
	
	@PostMapping	
	public RelatorioOrientacao salvar(@RequestBody RelatorioOrientacao relatorioOrientacao) {
		return relatorioOrientacaoService.salvar(relatorioOrientacao);
	} 
	
	@DeleteMapping(value = "/{id}")	
	public boolean apagar(@PathVariable("id") Long id) {
		return relatorioOrientacaoService.apagar(id);
	}

	@GetMapping(value = "/{id}")
	public RelatorioOrientacao recuperar(@PathVariable("id") Long id) {
		return relatorioOrientacaoService.recuperar(id);
	}
	
	@GetMapping
	public List<RelatorioOrientacao> listarPorIdTcc(@RequestParam("idTcc") Long idTcc) {
		return relatorioOrientacaoService.listarPorIdTcc(idTcc);
	}
}
