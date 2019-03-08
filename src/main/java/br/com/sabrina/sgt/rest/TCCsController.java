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
import br.com.sabrina.sgt.entidade.TCC;
import br.com.sabrina.sgt.service.TCCsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tccs")
public class TCCsController {

	@Autowired
	protected TCCsService tccService;
	
	@PostMapping	
	public TCC salvar(@RequestBody TCC tcc) {
		return tccService.salvar(tcc);
	} 
	
	@DeleteMapping(value = "/{id}")	
	public boolean apagar(@PathVariable("id") Long id) {
		return tccService.apagar(id);
	}

	@GetMapping(value = "/{id}")
	public TCC recuperar(@PathVariable("id") Long id) {
		return tccService.recuperarPorId(id);
	}

	@GetMapping(value = "/pesquisa")
	public List<TCC> pesquisa(@RequestParam("termo") String termo, @RequestParam("campo") CampoEnum campo) {
		return tccService.pesquisar(termo, campo);
	}

	@GetMapping
	public List<TCC> listarTodos() {
		return tccService.listarTodos();
	}

}
