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

import br.com.sabrina.sgt.entidade.Instituicao;
import br.com.sabrina.sgt.service.InstituicoesService;

@RestController
@CrossOrigin("*")
@RequestMapping("/instituicoes")
public class InstituicoesController {
	@Autowired
	protected InstituicoesService instituicoesService;
	
	@PostMapping	
	public Instituicao salvar(@RequestBody Instituicao instituicao) {
		return instituicoesService.salvar(instituicao);
	}
	
	@DeleteMapping(value = "/{id}")	
	public boolean apagar(@PathVariable("id") Long id) {
		return instituicoesService.apagar(id);
	}
	
	@GetMapping(value = "/{id}")
	public Instituicao recuperar(@PathVariable("id") Long id) {
		return instituicoesService.recuperarPorId(id);
	}

	@GetMapping(value = "/pesquisa")
	public List<Instituicao> pesquisa(@RequestParam("termo") String termo) {
		return instituicoesService.pesquisar(termo);
	}

	@GetMapping
	public List<Instituicao> listarTodos() {
		return instituicoesService.listarTodos();
	}
}
