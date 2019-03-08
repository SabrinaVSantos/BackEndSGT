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

import br.com.sabrina.sgt.entidade.Professor;
import br.com.sabrina.sgt.service.ProfessoresService;

@RestController
@CrossOrigin("*")
@RequestMapping("/professores")
public class ProfessoresController {
	@Autowired
	protected ProfessoresService professoresService;
	
	@PostMapping	
	public Professor salvar(@RequestBody Professor professor) {
		return professoresService.salvar(professor);
	} 
	
	@DeleteMapping(value = "/{id}")	
	public boolean apagar(@PathVariable("id") Long id) {
		return professoresService.apagar(id);
	}

	@GetMapping(value = "/{id}")
	public Professor recuperar(@PathVariable("id") Long id) {
		return professoresService.recuperarPorId(id);
	}

	@GetMapping(value = "/pesquisa")
	public List<Professor> pesquisa(@RequestParam("termo") String termo) {
		return professoresService.pesquisar(termo);
	}

	@GetMapping
	public List<Professor> listarTodos() {
		return professoresService.listarTodos();
	}

	@GetMapping(value = "/sende")
	public List<Professor> listarSeNDE() {
		return professoresService.listarSeNDE();
	}	
}
