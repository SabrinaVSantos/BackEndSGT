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

import br.com.sabrina.sgt.dto.CampoEnum;
import br.com.sabrina.sgt.entidade.PreProjeto;
import br.com.sabrina.sgt.service.PreProjetosService;

@RestController
@CrossOrigin("*")
@RequestMapping("/preprojetos")
public class PreProjetosController {

	@Autowired
	protected PreProjetosService preProjetoService;
	
	@PostMapping	
	public PreProjeto salvar(@RequestBody PreProjeto PreProjeto) {
		return preProjetoService.salvar(PreProjeto);
	} 
	
	@DeleteMapping(value = "/{id}")	
	public boolean apagar(@PathVariable("id") Long id) {
		return preProjetoService.apagar(id);
	}

	@GetMapping(value = "/{id}")
	public PreProjeto recuperar(@PathVariable("id") Long id) {
		return preProjetoService.recuperarPorId(id);
	}

	@GetMapping(value = "/pesquisa")
	public List<PreProjeto> pesquisa(@RequestParam("termo") String termo, @RequestParam("campo") CampoEnum campo) {
		return preProjetoService.pesquisar(termo, campo);
	}

	@GetMapping
	public List<PreProjeto> listarTodos() {
		return preProjetoService.listarTodos();
	}

}
